package br.com.gcs.ms_antifraud_service.domain.enums;

public enum Status {

    PENDING("Transação pendente de avaliação"),
    APPROVED("Transação aprovada"),
    DENIED("Transação negada devido à suspeita de fruade");

    private final String reason;

    Status(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
