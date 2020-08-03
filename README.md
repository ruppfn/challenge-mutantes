# Challenge Mutantes

API REST que detecta si un humano es mutante si encuentra una secuencia de cuatro letras iguales de forma oblicua, horizontal o vertical. 

[Consignas](https://github.com/bshyn/challenge-mutantes/blob/master/Challenge_Mutantes_API_Rest.pdf)

## Probar
Mutante (HTTP 200)
```bash
curl -X POST -i -d '{"dna": ["aaaa", "aaaa", "aaaa", "aaaa"]}' -H "Content-Type: application/json" http://frupp.com.ar/mutant
```
No mutante (HTTP 403)
```bash
curl -X POST -i -d '{"dna": ["aaca", "cgat", "taga", "tttg"]}' -H "Content-Type: application/json" http://frupp.com.ar/mutant  
```
