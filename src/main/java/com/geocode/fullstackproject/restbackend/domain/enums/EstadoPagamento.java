package com.geocode.fullstackproject.restbackend.domain.enums;

public enum EstadoPagamento {

  PENDENTE(1, "Pedido aguardando pagamento"), QUITADO(2, "Pedido quitado"), CANCELADO(3, "Pedido cancelado");

  private int cod;
  private String descricao;

  private EstadoPagamento(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }

  public static EstadoPagamento toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    for (EstadoPagamento t : EstadoPagamento.values()) {
      if (t.getCod() == cod)
        return t;
    }

    throw new IllegalArgumentException("Código invalido: " + cod);

  }

}
