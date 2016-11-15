package software.dygzt.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncodeUtil {

	/**
	 * 将一个String值转为Base64加密后的code
	 * 
	 * @param code
	 * @return
	 */
	public static String encode(String value) {
		if (null == value) {
			return null;
		}

		BASE64Encoder encode = new BASE64Encoder();

		return encode.encode(value.getBytes());
	}
	public static String doMd5(String str){
		return MD5.getMD5(str.getBytes());
	}
	/**
	 * 将一个byte数组转为Base64加密后的code
	 * 
	 * @param value
	 * @return
	 */
	public static String encode(byte[] value) {
		if (null == value) {
			return null;
		}
		BASE64Encoder encode = new BASE64Encoder();
		return encode.encode(value);
	}

	/**
	 * 将一个code值用Base64解密
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static String decode(String code) throws IOException {
		if (null == code) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();

		return new String(decoder.decodeBuffer(code));
	}

	/**
	 * 将String用Base64解码成byte数组
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static byte[] decodeToByte(String code) throws IOException {
		if (null == code) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();

		return decoder.decodeBuffer(code);
	}
	
	
	/**
	 * 将某个对象的属性全部进行Base64编码
	 * 
	 * @param c
	 * @param o
	 * @throws Exception
	 */
//	@SuppressWarnings("rawtypes")
//	public static void encodeObject(Class c, Object o) throws Exception {
//		if (null == o) {
//			return;
//		}
//		Field[] fields = c.getDeclaredFields();
//		for (Field f : fields) {
//			f.setAccessible(true);
//			Class type = f.getType();
//			if (type == String.class) {
//				//文书内容已经做过Base64编码了
//				if(StringUtil.equals(f.getName(), "WSNR")){
//					continue;
//				}
//				Object value = f.get(o);
//				if (null != value) {
//					String s = f.get(o).toString();
//					String result = encode(s);
//					f.set(o, result);
//				}
//			} else if (type == List.class) {
//				dealWithList(f, o,"encode");
//			} else {
//				encodeObject(type, f.get(o));
//			}
//			
//		}
//	}

	/**
	 * 处理对数组进行编码的情况
	 * 
	 * @param f
	 * @param o
	 * @throws Exception
	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static void dealWithList(Field f, Object o, String type) throws Exception {
//		Type ft = f.getGenericType();
//		Class elementClass = null;
//		if (ft instanceof ParameterizedType) {
//			ParameterizedType pt = (ParameterizedType) ft;
//			elementClass = (Class) pt.getActualTypeArguments()[0];
//		}
//		// 第一个接口
//		if (elementClass == SimpleCase.class) {
//			List<SimpleCase> lists = (List<SimpleCase>) f.get(o);
//			if (null ==lists){
//				return;
//			}
//			for (SimpleCase scase : lists) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(SimpleCase.class, scase);
//				}else{
//					decodeObject(SimpleCase.class, scase);
//				}
//				
//			}
//		}// 第二个接口
//		else if (elementClass == CaseInfo.class) {
//			List<CaseInfo> cases = (List<CaseInfo>) f.get(o);
//			if (null ==cases){
//				return;
//			}
//			for (CaseInfo caseInfo : cases) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(CaseInfo.class, caseInfo);
//				}else{
//					decodeObject(CaseInfo.class, caseInfo);
//				}
//			}
//		} else if (elementClass == YsDSRInfo.class) {
//			List<YsDSRInfo> dsrs = (List<YsDSRInfo>) f.get(o);
//			if (null ==dsrs){
//				return;
//			}
//			for (YsDSRInfo dsr : dsrs) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(YsDSRInfo.class, dsr);
//				}else{
//					decodeObject(YsDSRInfo.class, dsr);
//				}
//			}
//		} else if (elementClass == Write.class) {
//			List<Write> writes = (List<Write>) f.get(o);
//			if (null ==writes){
//				return;
//			}
//			for (Write write : writes) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(Write.class, write);
//				}else{
//					decodeObject(Write.class, write);;
//				}
//			}
//		}// 第三个接口
//		else if (elementClass == ERecordAddress.class) {
//			List<ERecordAddress> addresses = (List<ERecordAddress>) f.get(o);
//			if (null ==addresses){
//				return;
//			}
//			for (ERecordAddress address : addresses) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(ERecordAddress.class, address);
//				}else{
//					decodeObject(ERecordAddress.class, address);
//				}
//			}
//		}// 第四个接口
//		else if (elementClass == CaseStatus4.class) {
//			List<CaseStatus4> cases = (List<CaseStatus4>) f.get(o);
//			if (null ==cases){
//				return;
//			}
//			for (CaseStatus4 caseStatus : cases) {
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(CaseStatus4.class, caseStatus);
//				}else{
//					decodeObject(CaseStatus4.class, caseStatus);
//				}
//			}
//		}else if (elementClass == CaseInfo4.class){
//			List<CaseInfo4> cases = (List<CaseInfo4>) f.get(o);
//			if (null == cases){
//				return;
//			}
//			for(CaseInfo4 caseInfo:cases){
//				if (StringUtil.equals(type, "encode")){
//					encodeObject(CaseInfo4.class, caseInfo);
//				}else{
//					decodeObject(CaseInfo4.class, caseInfo);
//				}
//			}
//		}
//	}

	/**
	 * 将某个对象的属性全部进行Base64解码
	 * 
	 * @param c
	 * @param o
	 * @throws Exception
	 */
//	@SuppressWarnings("rawtypes")
//	public static void decodeObject(Class c, Object o) throws Exception {
//		if (null == o) {
//			return;
//		}
//		Field[] fields = c.getDeclaredFields();
//		for (Field f : fields) {
//			f.setAccessible(true);
////			System.out.println(f.getName() + f.getType());
//			Class type = f.getType();
//			if (type == String.class) {
//				//文书内容已经做过Base64解码了
//				if(StringUtil.equals(f.getName(), "WSNR")){
//					continue;
//				}
//				Object value = f.get(o);
//				if (null != value) {
//					String s = f.get(o).toString();
//					String result = decode(s);
//					f.set(o, result);
//				}
//			}else if (type == List.class) {
//				dealWithList(f, o,"decode");
//			}  else {
//				decodeObject(type, f.get(o));
//			}
//		}
//
//	}
}
