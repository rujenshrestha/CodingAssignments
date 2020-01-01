package assignments.computernetworks.tcpchecksum;

import java.io.*;

public class TCPData implements Serializable{

	private static final long serialVersionUID = 1L;
	public String data = "";
	public int checksum = 0;
	public static boolean keepAlive = true;
	
	public boolean isKeepAlive() {
		return keepAlive;
	}
	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getChecksum() {
		return checksum;
	}
	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}
	
	public void verifyChecksum(){
			
			System.out.println("data and checksum to be verified:"+this.data+this.checksum);
	        int reChecksum = computeChecksum(this.data);
	        reChecksum = Integer.parseInt("FFFF", 16) - reChecksum;
	        System.out.println("Data:"+this.data+"checksum:"+this.checksum+" Regenerated Checksum:"+reChecksum);
	        int result = reChecksum + this.checksum;
	        result = Integer.parseInt("FFFF", 16) - result;
	        System.out.println("result = " + Integer.toHexString(result));
	        if (result == 0){
	            System.out.println("There is no error in DATA.");
	        }else{
	            System.out.println("There is a error in DATA.");
	        }
	}
	
	
	public int computeChecksum(String data){
        String hexData = "";
        int chVal=0;
        int i =0;
        int checksum=0;
        
        while(i < data.length() - 2){
        	chVal = (int) data.charAt(i);
            hexData = Integer.toHexString(chVal);
            chVal = (int) data.charAt(i + 1);
            hexData = hexData + Integer.toHexString(chVal);
            System.out.println(data.charAt(i) + "" + data.charAt(i + 1) + " : " + hexData);
            chVal = Integer.parseInt(hexData, 16);
            checksum += chVal;
            i += 2;
        }
        if (data.length() % 2 == 0){
        	chVal = (int) data.charAt(i);
            hexData = Integer.toHexString(chVal);
            chVal = (int) data.charAt(i + 1);
            hexData = hexData + Integer.toHexString(chVal);
            System.out.println(data.charAt(i) + "" + data.charAt(i + 1) + " : "+ hexData);
            chVal = Integer.parseInt(hexData, 16);
        }else{
            
        	chVal = (int) data.charAt(i);
            hexData = "00" + Integer.toHexString(chVal);
            chVal = Integer.parseInt(hexData, 16);
            System.out.println(data.charAt(i) + " : " + hexData);
        }
        checksum += chVal;
        hexData = Integer.toHexString(checksum);
        if (hexData.length() > 4){
            int carry = Integer.parseInt(("" + hexData.charAt(0)), 16);
            hexData = hexData.substring(1, 5);
            checksum = Integer.parseInt(hexData, 16);
            checksum += carry;
        }
        checksum = Integer.parseInt("FFFF", 16) - checksum;
        return checksum;
    }
}
