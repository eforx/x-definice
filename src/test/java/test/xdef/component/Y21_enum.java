// This enumeration was generated by org.xdef.component.GenXComponent
// from declared parser type name: eType.
// Any modifications to this file will be lost upon recompilation.
package test.xdef.component;
public enum Y21_enum implements org.xdef.component.XCEnumeration {
	x,
	y,
	A1_b2,
	A1_b,
	z,
	_1,
	$;
	@Override
	public final Object itemValue() {return name();}
	@Override
	public final String toString() {return name();}
	public static final Y21_enum toEnum(final Object x) {
		if (x != null)
			for(Y21_enum y: values())
				if (y.itemValue().toString().equals(x.toString())) return y;
		return null;
	}
}