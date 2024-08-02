/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Order;

/**
 *
 * @author hbtth
 */
public class Payment {
    private int payment_id;
    private long vnp_Amount;
    private String vnp_BankCode;
    private String vnp_BankTranNo;
    private String vnp_PayDate;
    private String vnp_OrderInfo;
    private String vnp_TransactionStatus;
    private String vnp_TxnRef;

    public Payment() {
    }

    public Payment(int payment_id, long vnp_Amount, String vnp_BankCode, String vnp_BankTranNo, String vnp_PayDate, String vnp_OrderInfo, String vnp_TransactionStatus, String vnp_TxnRef) {
        this.payment_id = payment_id;
        this.vnp_Amount = vnp_Amount;
        this.vnp_BankCode = vnp_BankCode;
        this.vnp_BankTranNo = vnp_BankTranNo;
        this.vnp_PayDate = vnp_PayDate;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_TransactionStatus = vnp_TransactionStatus;
        this.vnp_TxnRef = vnp_TxnRef;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public long getVnp_Amount() {
        return vnp_Amount;
    }

    public void setVnp_Amount(long vnp_Amount) {
        this.vnp_Amount = vnp_Amount;
    }

    public String getVnp_BankCode() {
        return vnp_BankCode;
    }

    public void setVnp_BankCode(String vnp_BankCode) {
        this.vnp_BankCode = vnp_BankCode;
    }

    public String getVnp_BankTranNo() {
        return vnp_BankTranNo;
    }

    public void setVnp_BankTranNo(String vnp_BankTranNo) {
        this.vnp_BankTranNo = vnp_BankTranNo;
    }

    public String getVnp_PayDate() {
        return vnp_PayDate;
    }

    public void setVnp_PayDate(String vnp_PayDate) {
        this.vnp_PayDate = vnp_PayDate;
    }

    public String getVnp_OrderInfo() {
        return vnp_OrderInfo;
    }

    public void setVnp_OrderInfo(String vnp_OrderInfo) {
        this.vnp_OrderInfo = vnp_OrderInfo;
    }

    public String getVnp_TransactionStatus() {
        return vnp_TransactionStatus;
    }

    public void setVnp_TransactionStatus(String vnp_TransactionStatus) {
        this.vnp_TransactionStatus = vnp_TransactionStatus;
    }

    public String getVnp_TxnRef() {
        return vnp_TxnRef;
    }

    public void setVnp_TxnRef(String vnp_TxnRef) {
        this.vnp_TxnRef = vnp_TxnRef;
    }
    
    
}
