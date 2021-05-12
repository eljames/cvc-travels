# cvc-travels
CVC Travels application

APIs disponíveis:

Endpoint 1: Obter todos os hoteis dispoíveis em uma determinada cidade  
GET http://localhost:8080/api/hotels/city/{cityId}?checkInDate=[checkInDate]&checkOutDate=[checkOutDate]&numberOfAdults=[numberOfAdults]&numberOfChildren=[numberOfChildren]  
  
cityId = ID da cidade  
checkInDate = data de inicio da entrada ao hotel  
checkOutDate = data em que será o último dia no hotel  
numberOfAdults = número de adultos  
numberOfChildren = número de crianças acompanhadas.  
  
Retorno:  
```json
[  
    {  
        "id": 4,  
        "city": "Porto Seguro",  
        "name": "Hotel Teste 4",  
        "rooms": [  
            {  
                "id": 0,  
                "category": {  
                    "name": "Standard"  
                },  
                "totalPrice": 30506.02,  
                "priceDetail": {  
                    "pricePerDayAdult": 488.23,  
                    "pricePerDayChild": 1117.35  
                }  
            }  
        ]  
    }  
]  
```
Informações do retorno:  
  
id = ID do hotel  
city = cidade do hotel  
name = nome do hotel  
rooms = quartos disponívels  
rooms[].id = ID do quarto  
rooms[].category = categoria  
rooms[].totalPrice = Preço total. Calculado utilizando => ((pricePerDayAdult * numberOfAdults) + (pricePerDayChild * numberOfChildren)) * quantidadeDeDias. A quantidadeDeDias é calculando utilizando a diferença de dias entre checkInDate e checkOutDate.  
priceDetail.pricePerDayAdult = Preço por adulto em um dia.  
priceDetail.pricePerDayChild = Preço por criança em um dia.  

Endpoint 2: Obter informações de um hotel específico  
GET http://localhost:8080/api/hotels/{hotelId}?checkInDate=[checkInDate]&checkOutDate=[checkOutDate]&numberOfAdults=[numberOfAdults]&numberOfChildren=[numberOfChildren]  

hotelId: ID do hotel. O ID do hotel pode ser obtida pela Endpoint 1, listando as cidades.  
Os outros parâmtros estão descritos na Endpoint 1.  
O formato do retorno é o mesmo que do Endpoint 1, com a diferença que este endpoint filtra apenas por um hotel específico.  
