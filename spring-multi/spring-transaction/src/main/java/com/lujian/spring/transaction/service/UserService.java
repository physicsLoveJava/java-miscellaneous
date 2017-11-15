package com.lujian.spring.transaction.service;

public interface UserService {

    void localTransaction();

    void localTransactionWrong();

    void programTransaction();

    void declareTransactionRequired();

    void declareTransactionNested();

    void declareTransactionMandatory();

    void declareTransactionSupports();

    void declareTransactionRequiresNew();

    void declareTransactionNotSupported();

    void declareTransactionNever();
}
