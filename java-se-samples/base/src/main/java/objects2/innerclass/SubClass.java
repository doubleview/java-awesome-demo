package objects2.innerclass;


public class SubClass extends Out.In {
    //鏄剧ず瀹氫箟SubClass鐨勬瀯閫犲櫒
    public SubClass(Out out) {
        //閫氳繃浼犲叆鐨凮ut瀵硅薄鏄惧紡璋冪敤In鐨勬瀯閫犲櫒
        out.super("hello");
    }
}
