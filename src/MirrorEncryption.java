import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MirrorEncryption 
{
    public static char[][] matrix = new char[15][15];
    public static String top = "abcdefghijklm"; // top matrix[0][1]
    public static String left = "ABCDEFGHIJKLM"; // left matrix[1][0]
    public static String right = "nopqrstuvwxyz";
    public static String bottom = "NOPQRSTUVWXYZ";
    public static int[][] location;// = new int[1][1];

    public static void main(String[] args) 
    {
        try 
        {
            getMatrix();
            readFile("input.txt");
            //findChar('M');
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }
    
    public static void mirror(int x, int y, int rOff, int cOff)
    {
    	StringBuilder str = new StringBuilder();
    	do
    	{
			
			//heading down
			if(rOff == 1)
			{
				x += rOff;
				if(matrix[x][y] == '/')//move left
				{
					rOff = 0;
					cOff = -1;
				}else if (matrix[x][y] == '\\') //move right
					{
						rOff = 0;
						cOff = 1;
					}
			}
			//heading up
			else if(rOff == -1)
			{
				x += rOff;
				if(matrix[x][y] == '/')//move right
				{
					rOff = 0;
					cOff = 1;
				}else if (matrix[x][y] == '\\') //move left
					{
						rOff = 0;
						cOff = -1;
					}
			}
			//heading right
			else if(cOff == 1)
			{
				y += cOff;
				if(matrix[x][y] == '/')//move up
				{
					rOff = -1;
					cOff = 0;
				}else if (matrix[x][y] == '\\') //move down
					{
						rOff = 1;
						cOff = 0;
					}
			}
			//heading left
			else if(cOff == -1)
			{
				y += cOff;
				if(matrix[x][y] == '/')//move down
				{
					rOff = 1;
					cOff = 0;
				}else if (matrix[x][y] == '\\') //move up
					{
						rOff = -1;
						cOff = 0;
					}
			}
		
    	}
    	while(x > 0 && y > 0 && x < 14 && y < 14 );
    	str.append(matrix[x][y]);
    	System.out.print(matrix[x][y]);
    }
    
    
    public static void readFile(String input) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new FileReader(input));
    	int num = 0;
    	char ch;
    	while((num=br.read()) != -1)
    	{
    		ch = (char)num;
    		findChar(ch);
    	}
    	br.close();
    }
    
    public static void findChar(char ch)
    {
    	if(top.indexOf(ch) >= 0)
    	{
    		int coordinate = top.indexOf(ch) + 1;
    		mirror(0, coordinate, 1, 0);
    
    	}else if(bottom.indexOf(ch) >= 0)
    	{
    		int coordinate = bottom.indexOf(ch) + 1;
    		mirror(14, coordinate, -1, 0);
    		
    	}else if(right.indexOf(ch) >= 0)
    	{
    		int coordinate = right.indexOf(ch) + 1;
    		mirror(coordinate, 14, 0, -1);
    		
    	}else if(left.indexOf(ch) >= 0)
    	{
    		int coordinate = left.indexOf(ch) + 1;
    		mirror(coordinate, 0, 0, 1);
    	}
    	
    	
    }
    
    
    public static void getMatrix() throws IOException
    {
        
        int count = 1;
        
        while(count < 14)
        {
            matrix[0][count] = top.charAt(count-1);
            matrix[14][count] = bottom.charAt(count-1);
            count++;
        }
        
        count = 1;
        while(count < 14)
        {
            matrix[count][0] = left.charAt(count-1);
            matrix[count][14] = right.charAt(count-1);
            count++;
        }
        
        String line;
        int j = 1;
        
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/Reber/workspace/MirrorEncryption/EncryptionMatrix.txt"));
        
        while ((line = br.readLine()) != null) 
        {
        	
                for(int i = 1; i <= line.length();i++)
                {
                    matrix[j][i] = line.charAt(i-1);
                }
                j++;
        }
        
        br.close();
        
        
        for(int i = 0; i < matrix.length; i++)
        {
            for(int y = 0; y < matrix.length;y++)
            {
                System.out.print(matrix[i][y]);        
            }
            System.out.println("");
        }

        
    }

}
