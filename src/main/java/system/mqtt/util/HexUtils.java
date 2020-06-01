package system.mqtt.util;

import java.math.BigInteger;

public class HexUtils {
	/**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
   /**
    * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
    * hexStrToByteArr(String strIn) 互为可逆的转换过程
    *
    * @param arrB 需要转换的byte数组
    * @return 转换后的字符串
    * @throws Exception 本方法不处理任何异常，所有异常全部抛出
    */
   private static String byteArrToHexStr(byte[] arrB) throws Exception {
       int iLen = arrB.length;
       // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
       StringBuffer sb = new StringBuffer(iLen * 2);
       for (int i = 0; i < iLen; i++) {
           int intTmp = arrB[i];
           // 把负数转换为正数
           while (intTmp < 0) {
               intTmp = intTmp + 256;
           }
           // 小于0F的数需要在前面补0
           if (intTmp < 16) {
               sb.append("0");
           }
           sb.append(Integer.toString(intTmp, 16));
       }
       return sb.toString();
   }

   /**
    * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
    * 互为可逆的转换过程
    *
    * @param strIn 需要转换的字符串
    * @return 转换后的byte数组
    * @throws Exception 本方法不处理任何异常，所有异常全部抛出
    * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
    */
   private static byte[] hexStrToByteArr(String strIn) throws Exception {
       byte[] arrB = strIn.getBytes();
       int iLen = arrB.length;

       // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
       byte[] arrOut = new byte[iLen / 2];
       for (int i = 0; i < iLen; i = i + 2) {
           String strTmp = new String(arrB, i, 2);
           arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
       }
       return arrOut;
   }
   
   
   
   /**
    * byte数组转换为二进制字符串 无逗号分割
    **/
   public static String byteArrToBinStr(byte[] b) {
       StringBuffer result = new StringBuffer();
       for (int i = 0; i < b.length; i++) {
           result.append(String.format("%08d", Long.parseLong(Long.toString(b[i] & 0xff, 2))));
       }
       return result.toString();
   }
   
   /**
    * byte数组转换为二进制字符串,每个字节以","隔开
    **/
   public static String byteArrToBinStrTest(byte[] b) {
       StringBuffer result = new StringBuffer();
       for (int i = 0; i < b.length; i++) {
           result.append(Long.toString(b[i] & 0xff, 2) + ",");
       }
       return result.toString().substring(0, result.length() - 1);
   }

   /**
    * 二进制字符串转换为byte数组,每个字节以","隔开
    **/
   public static byte[] binStrToByteArr(String binStr) {
       String[] temp = binStr.split(",");
       byte[] b = new byte[temp.length];
       for (int i = 0; i < b.length; i++) {
           b[i] = Long.valueOf(temp[i], 2).byteValue();
       }
       return b;
   }
   
   
   public static int inaryToDecimal(int binaryNumber){
	   
	    int decimal = 0;
	    int p = 0;
	    while(true){
	      if(binaryNumber == 0){
	        break;
	      } else {
	          int temp = binaryNumber%10;
	          decimal += temp*Math.pow(2, p);
	          binaryNumber = binaryNumber/10;
	          p++;
	       }
	    }
	    return decimal;
	  }
    
   
   /**
	 * 将01字符串的长度增长为8的倍数，不足部分在末尾追回‘0’。
	 * @param input
	 * @return
	 */
	static String formatbef(String input) {
		int remainder = input.length() % 8;  
		StringBuilder sb = new StringBuilder(input);
		if (remainder > 0)
			for (int i = 0; i < 8 - remainder; i++)
				sb.insert(0, "0");
		return sb.toString();
	}

   
   /**
	 * 将01字符串的长度增长为8的倍数，不足部分在末尾追回‘0’。
	 * @param input
	 * @return
	 */
	static String format(String input) {
		int remainder = input.length() % 8;  
		StringBuilder sb = new StringBuilder(input);
		if (remainder > 0)
			for (int i = 0; i < 8 - remainder; i++)
				sb.append("0"); 
		return sb.toString();
	}

	/**
	 * 二制度字符串转字节数组，如 101000000100100101110000 -> A0 09 70
	 * @param input 输入字符串。
	 * @return 转换好的字节数组。
	 */
	static byte[] string2bytes(String input) { 
		StringBuilder in = new StringBuilder(input); 
		// 注：这里in.length() 不可在for循环内调用，因为长度在变化 
		int remainder = in.length() % 8; 
		if (remainder > 0)
			for (int i = 0; i < 8 - remainder; i++)
				in.append("0"); 
		byte[] bts = new byte[in.length() / 8];

		// Step 8 Apply compression
		for (int i = 0; i < bts.length; i++)
			bts[i] = (byte) Integer.parseInt(in.substring(i * 8, i * 8 + 8), 2);

		return bts; 
	}
	
	/**
	 * 字节数组转字符串，如 A0 09 70 -> 101000000000100101110000。
	 * @param bts 转入字节数组。
	 * @return 转换好的只有“1”和“0”的字符串。
	 */
	static String bytes2String(byte[] bts) {
		String[] dic = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
				"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" };
		StringBuilder out = new StringBuilder();
		for (byte b : bts) {
			String s = String.format("%x", b);
			s = s.length() == 1? "0" + s: s;
			out.append(dic[Integer.parseInt(s.substring(0, 1), 16)]);
			out.append(dic[Integer.parseInt(s.substring(1, 2), 16)]);
		}
		return out.toString();
	}
	
	//將10進制轉換為16進制
		public static String encodeHEX(Integer numb){
			
			String hex= Integer.toHexString(numb);
			return hex;
			
		}
		
		//將16進制字符串轉換為10進制數字
		public static int decodeBit(String hexs){
			BigInteger bigint=new BigInteger(hexs, 10);
			int numb=bigint.intValue();
				return numb;
			}
		
		//將16進制字符串轉換為10進制數字
	public static int decodeHEX(String hexs){
		BigInteger bigint=new BigInteger(hexs, 16);
		int numb=bigint.intValue();
			return numb;
		}
   
}
