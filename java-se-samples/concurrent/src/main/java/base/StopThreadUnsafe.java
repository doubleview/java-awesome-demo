package base;


/**
 * 浣跨敤stop鏂规硶浼氬鑷寸嚎绋嬬珛鍒诲仠姝紝骞堕噴鏀鹃攣
 */
public class StopThreadUnsafe {

    public static User u = new User();

    public static class User {

        private int id;
        private String name;


        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread {

        public void run() {
            while (true) {
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis()) / 1000;
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }


    public static class ReadObjectThread extends Thread {
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
