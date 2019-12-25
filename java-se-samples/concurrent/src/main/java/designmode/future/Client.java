package designmode.future;


public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread(){
            public void run(){
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("name");
        System.out.println("璇锋眰瀹屾瘯");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("鏁版嵁 = " + data.getResult());
    }
}
