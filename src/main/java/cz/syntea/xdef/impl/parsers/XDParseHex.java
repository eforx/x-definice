package cz.syntea.xdef.impl.parsers;

import cz.syntea.xdef.msg.XDEF;
import cz.syntea.xdef.XDParseResult;
import cz.syntea.xdef.proc.XXNode;
import cz.syntea.xdef.impl.code.DefBytes;

/** Parser of X-Script "hexBinary" type.
 * @author Vaclav Trojan
 */
public class XDParseHex extends XSParseBase64Binary {
	private static final String ROOTBASENAME = "hex";

	public XDParseHex() {super();}
	@Override
	public void parseObject(final XXNode xnode, final XDParseResult p){
		int pos0 = p.getIndex();
		p.isSpaces();
		int pos1 = p.getIndex();
		int pos2 = pos1;
		int len = 0;
		while (p.isOneOfChars("0123456789ABCDEFabcdef") != 0) {
			len++;
			pos2 = p.getIndex();
			p.isSpaces();
		}
		String s;
		byte[] bytes;
		if (len == 0) {
			if (_minLength == 0) {
				p.setParsedValue(new DefBytes(new byte[0]));
				s = "";
				bytes = new byte[0];
			} else {
				p.error(XDEF.XDEF809, parserName());//Incorrect value of '&{0}'
				return;
			}
		} else {
			int b = 0;
			boolean first = true;
			len = (len + 1) / 2;
			bytes = new byte[len];
			s = p.getBufferPart(pos1, pos2);
			for (int i = s.length() - 1, k = len; i >= 0; i--) {
				char c;
				if (((c = s.charAt(i)) <= ' ')) {
					continue;
				}
				int n = "0123456789ABCDEFabcdef".indexOf(c);
				if (n > 15) {
					n -= 6;
				}
				if (first = !first) {
					b = n*16 + b;
					bytes[--k] = (byte) b;
				} else {
					b = n;
				}
			}
			if (!first) {
				bytes[0] = (byte) b;
			}
		}
		p.replaceParsedBufferFrom(pos0, s);
		p.setParsedValue(new DefBytes(bytes));
		check(p);
	}
	@Override
	public String parserName() {return ROOTBASENAME;}
}
