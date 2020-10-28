# NanoBolsa

É um sistema de bolsa para o seu servidor, que tem como função oferecer um bonus conforme o valor atual da mesma.

## Comandos

• `/bonus` - Sem permissão <br>
• `/bonus set <amount>` - bolsa.admin

## Placeholders

**PlaceholderAPI**
• %bolsa% - Retorna o valor da bolsa
`Caso use featherboard use {placeholderapi_bolsa}`

## Configuração

```yml
#Placeholderapi: %bolsa% (caso use featherboard: {placeholder_api_bolsa})  
#Comandos  
#  
# • /bolsa - Não precisa de permissão  
# • /bolsa set <amount> - bolsa.admin  
#  
# nanoplugins.com | nanoplugins.com.br | nanoplugins.fun  
  
#Definições do plugin  
settings:  
  delay: 300 #segundos (300 = 5 minutos)  
  value:  
    min: 1 #minimo da bolsa  
  max: 100 #maximo da bolsa  
  difference:  
    use: false #caso esteja true vai funcionar por diferença  
  value: 20 #Valor que adiciona ou subtrai da bolsa atual  
  
#Mensagens  
messages:  
  must-be-number: "&cPrecisa digitar um número!" #Se o valor definido não for um número  
  need-args: "&c/bolsa set <amount>" #Caso o comando seja mal executado  
  no-perm: "&cNão tem permissão!" #Sem permissão para usar o comando  
  check: "&eValor da bolsa: &f%stockMarket%" #Informação da bols aatual  
  update-message:  
    - "&e&lBolsa de Valores" #Mensagem de update da bolsa  
    - "&6A bolsa atualizou de &c&n%oldStockMarket%&6 para &a&n%stockMarket%&6!"
```

## API

**Pegar o valor atual da bolsa**
```java
NanoStockMarketAPI api = new NanoStockMarketAPI()
System.out.println("Valor atual da bolsa: " + api.getStockMarket());
```

**Evento quando muda o valor da bolsa**
`NanoStockMarketUpdate`
```java
@EventHandler  
public void onCall(NanoStockMarketUpdate event) {  
  System.out.println("Antigo valor da bolsa: " + event.getOldValue());  
  System.out.println("Novo valor da bolsa: " + event.getNewValue());  
}
```
