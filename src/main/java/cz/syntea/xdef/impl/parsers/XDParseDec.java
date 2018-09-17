package cz.syntea.xdef.impl.parsers;

import cz.syntea.xdef.msg.XDEF;
import cz.syntea.xdef.XDParseResult;
import cz.syntea.xdef.proc.XXNode;
import cz.syntea.xdef.impl.code.DefDecimal;

/** Parse decimal number with decimal point in X-Script. Decimal point may be
 * either '.' or ','
 * @author Vaclav Trojan
 */
//public class XDParseDec extends XSAbstractParseComparable {
public class XDParseDec extends XSParseDecimal {
	private static final String ROOTBASENAME = "dec";

	public XDParseDec() {super();}

	@Override
	public void parseObject(final XXNode xnode, final XDParseResult p) {
		int pos0 = p.getIndex();
		if (_whiteSpace == 'c') {
			p.isSpaces();
		}
		int pos = p.getIndex();
		int i = (p.isChar('-') || p.isChar('+')) ? 1 : 0;
		boolean wasdigit = false;
		while (p.isChar('0')) {
			i++;
			wasdigit = true; //digit recognized
		}
		int h = i; //first position
		while (p.isDigit() != -1) {
			i++;
			wasdigit = true; //digit recognized
		}
		int k = -1; //decimal point position
		if (p.isOneOfChars(".,") > 0) {
			k = i;
			i++;
			while (p.isDigit() != -1) {
				i++;
				wasdigit = true; //digit recognized
			}
		}
		String s = p.getParsedBufferPartFrom(pos);
		int j;
		if ((j = s.length() - 1) < 0) {
			p.error(XDEF.XDEF809, parserName());//Incorrect value of '&{0}'
			return;
		}
		if (!wasdigit || i <= j) {
			p.error(XDEF.XDEF809, parserName());//Incorrect value of '&{0}'
			return;
		}
		if (_totalDigits == -1) {
			if (_fractionDigits != -1) { // only fraction digits
				if (((k != -1) ? j - k : 0) < _fractionDigits) {
					//Value of '&{0}' doesn't fit to '&{1}'
					p.error(XDEF.XDEF813, parserName(), "fractionDigits");
					return;
				}
			}
		} else { //a prameter
			j = (k != -1) ? j - k : 0;
			i = (k != -1) ? k - h : i - h;
			if (!(_fractionDigits == -1 ? //dec(m)
				i + j <= _totalDigits : //dec(m, n)
				i <= _totalDigits - _fractionDigits && j <= _fractionDigits)) {
				//Value of '&{0}' doesn't fit to '&{1}'
				p.error(XDEF.XDEF813, parserName(), "totalDigits");
				return;
			}
		}
		if (_whiteSpace == 'c') {
			p.isSpaces();
		}
		p.replaceParsedBufferFrom(pos0, s);
		p.setParsedValue(new DefDecimal(
			(s.charAt(0) == '+'? s.substring(1) : s).replace(',', '.')));
		checkPatterns(p);
		checkComparable(p);
	}

	@Override
	public String parserName() {return ROOTBASENAME;}
	@Override
	public short parsedType() {return XD_DECIMAL;}

}