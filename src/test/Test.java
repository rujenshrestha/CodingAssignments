package test;

import java.util.Objects;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		  String s = "hello";
		  byte[] bytes = s.getBytes();//String binary = Integer.toBinaryString(45);
		  for(int i=0;i<bytes.length;i++){
			  System.out.println(bytes[i] );
		  }
		  //System.out.println(binary);
		  Test t = new Test();
		  t.calculateChecksum(bytes);
	}
	
	 public void calculateChecksum(byte[] buf) {
		    int length = buf.length;
		    int i = 0;

		    long sum = 0;
		    long word = 0 ;

		    // Handle all pairs
		    while (length > 1) {
		      // Corrected to include @Andy's edits and various comments on Stack Overflow
		    	word = (((buf[i] << 8) & 0xFF00) | ((buf[i + 1]) & 0xFF));
		      sum += word;
		      // 1's complement carry bit correction in 16-bits (detecting sign extension)
		      if ((sum & 0xFFFF0000) > 0) {
		        sum = sum & 0xFFFF;
		        sum += 1;
		      }

		      i += 2;
		      length -= 2;
		    }

		    // Handle remaining byte in odd length buffers
		    if (length > 0) {
		      // Corrected to include @Andy's edits and various comments on Stack Overflow
		      sum += (buf[i] << 8 & 0xFF00);
		      // 1's complement carry bit correction in 16-bits (detecting sign extension)
		      if ((sum & 0xFFFF0000) > 0) {
		        sum = sum & 0xFFFF;
		        sum += 1;
		      }
		    }

		    // Final 1's complement value correction to 16-bits
		    sum = ~sum;
		    sum = sum & 0xFFFF;
		    String chksum = Objects.toString(sum);
		    System.out.println("SUM:"+sum+" WORD:"+word+" chksum:"+chksum);

		  }

}
