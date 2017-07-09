package threadsyn.synchronizedBlock;

public class Account {
    // ��װ�˻���š��˻�����������Ա����
    private String accountNo;
    private double balance;

    public Account() {
    }

    // ������
    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    // �˴�ʡ����accountNo��balance��setter��getter����

    // accountNo��setter��getter����
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    // balance��setter��getter����
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    // ����������������accountNo����дhashCode()��equals()����
    public int hashCode() {
        return accountNo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj != null
                && obj.getClass() == Account.class) {
            Account target = (Account) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}