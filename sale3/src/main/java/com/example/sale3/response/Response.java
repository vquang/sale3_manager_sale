package com.example.sale3.response;

public class Response<Status, Data> {
    private Status status;
    private Data data;

    public Response() {
    }

    public Response(Status status, Data data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
