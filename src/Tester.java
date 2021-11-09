public class Tester {
    public static void main(String[] args) {

        char[]tmp = new char[] {'-','-','-','-','A','-','-','-'};
        boolean found = false;
        char searchedValue = 'A';

        for(int x : tmp){
            if(x == searchedValue){
                found = true;
                break;
            }
        }
        System.out.println(found);
        System.out.println(checksIfSymbolIncludes(tmp,'A'));
        System.out.println(V2(tmp,'A'));
    }

    public static boolean checksIfSymbolIncludes(char[] tmp,char search){
        for (int i = 0; i < tmp.length ; i++) {
            if (tmp[i] == search) return true;
        }
        return false;
    }

    public static boolean V2(char[] tmp,char search){
        for (int i = 0; i < tmp.length ; i++) {
            if (tmp[i] != search) return false;
        }
        return true;
    }



}
