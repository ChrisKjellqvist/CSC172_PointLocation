import java.io.*;

/**
 * Created by chris on 11/1/16.
 */
public class main implements IntersectionConstants{
    public static void main(String[] args) throws IOException {/*
        BinaryTree tree = new BinaryTree();
        int c;
        String in = "in.txt";
        String out = "out.txt";
        try {
            FileOutputStream os = new FileOutputStream(in, false);
            while((c=System.in.read()) != -1) {
                os.write(c);
            }
            os.close();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));
            int n = Integer.parseInt(bufferedReader.readLine());
            String ln;
            for (int i = 0; i < n; i++) {
                ln = bufferedReader.readLine();
                String[] spl = ln.split(" ");
                double[] ar = new double[4];
                for (int j = 0; j < 4; j++) {
                    ar[j] = Double.parseDouble(spl[j]);
                }
                tree.insert(new Point(ar[0], ar[1]), new Point(ar[2], ar[3]), i);
            }
            while ((ln = bufferedReader.readLine())!=null){
                String[] spl = ln.split(" ");
                double[] ar = new double[4];
                for (int j = 0; j < 4; j++) {
                    ar[j] = Double.parseDouble(spl[j]);
                }
                Point a = new Point(ar[0], ar[1]);
                Point b = new Point(ar[2], ar[3]);
                int pos1;
                int pos2;
                if ((pos1=tree.getPosition(a))==(pos2=tree.getPosition(b))){
                    bufferedWriter.write("-1\n");
                } else {
                    int xorPos = pos1 ^ pos2;
                    Node current = tree.root;
                    while((xorPos&1)!=1){
                        if ((pos1&1)==CLOCKWISE){
                            current = current.clockwise;
                        } else {
                            current = current.counterclockwise;
                        }
                        pos1>>=1;
                        xorPos>>=1;
                    }
                    bufferedWriter.write(current.line.name + "\n");
                }
            }
            FileInputStream is = new FileInputStream(out);
            while((c=is.read()) != -1) {
                System.out.write(c);
            }
            is.close();
            System.out.flush();
        }
        catch(Exception e) {

        }
        */
        BinaryTree tree = new BinaryTree();
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        String str;
        int i = Integer.parseInt(br.readLine());
        for(int j = 0; j < i; j++){
            str = br.readLine();
            String[] strspl = str.split(" ");
            Point p1 = new Point(Double.parseDouble(strspl[0]), Double.parseDouble(strspl[1]));
            Point p2 = new Point(Double.parseDouble(strspl[2]), Double.parseDouble(strspl[3]));
            tree.insert(p1, p2, i);
        }
        while((str=br.readLine())!=null){
            String[] strspl = str.split(" ");
            Point p1 = new Point(Double.parseDouble(strspl[0]), Double.parseDouble(strspl[1]));
            Point p2 = new Point(Double.parseDouble(strspl[2]), Double.parseDouble(strspl[3]));
            String str1;
            String str2;
            boolean isInSameRegion = (str1 =tree.getPosition(p1)).equals((str2=tree.getPosition(p2)));
            if (!isInSameRegion){
                String loc = "";
                for (int j = 0; j < str1.length(); j++) {
                    if (str1.charAt(j)==str2.charAt(j)){
                        loc+=str1.charAt(j);
                    } else {
                        break;
                    }
                }
                Node c = tree.root;
                for (int j = 0; j < loc.length(); j++) {
                    if (loc.charAt(j)==COUNTER_CLOCKWISE){
                        c = c.counterclockwise;
                    } else {
                        c = c.clockwise;
                    }
                }
                try {
                    System.out.println(c.line.name);
                } catch (NullPointerException e){
                    System.out.println(loc);
                    System.out.println(c);
                    System.exit(0);
                }
            } else {
                System.out.println("-1\n");
            }
        }
    }
}
