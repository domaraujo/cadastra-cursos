# CadastraCursos API



Essa API realiza a consulta, inclusão, alteração e exclusão de cursos em um database H2.
Em seu desenvolvimento foi utilizado o framework Spring Boot, JPA e Hibernate e JDK11.

Para minimizar código boilerplate foi 
utilizado Lombok e ModelMapper e para os testes de unidade JUnit5 e Mockito.

###

### Curso    
######Operações do domínio Curso:

| Método   | Endpoint                        | Descrição              |
|:---------|:--------------------------------|:-----------------------|
| `GET`    | http://localhost:8080/curso     | Lista todos os cursos. |
| `GET`    | http://localhost:8080/curso/1   | Busca curso pelo ID.   |
| `POST`   | http://localhost:8080/curso     | Cadastra curso.        |
| `PUT`    | http://localhost:8080/curso/1   | Atualiza curso.        |
| `DELETE` | http://localhost:8080/curso/1   | Exclui curso pelo ID.  |


### Body:
######Exemplo de valores:

| `POST`   | http://localhost:8080/curso     | Cadastra curso.        |

```javascript
    {
        "descricaoDoAssunto": "Java",
        "dataInicio": "2024-11-01",
        "dataTermino": "2024-11-10",
        "quantidadeAlunoPorTurma": 50,
        "categoriaModel": {
            "codigo": 1
        }
    }
```

| `PUT`    | http://localhost:8080/curso/1   | Atualiza curso.        |

```javascript
    {
        "descricaoDoAssunto": "Scrum",
        "dataInicio": "2023-02-01",
        "dataTermino": "2023-02-10",
        "quantidadeAlunoPorTurma": 50,
        "categoriaModel": {
            "codigo": 3
        }
    }
```

			        

### Categoria
######Operações do domínio Categoria:

| Método   | Endpoint                            | Descrição                    |
|:---------|:------------------------------------|:-----------------------------|
| `GET`    | http://localhost:8080/categoria     | Lista todos as categorias.   |
| `GET`    | http://localhost:8080/categoria/1   | Busca categoria pelo ID.     |
| `POST`   | http://localhost:8080/categoria     | Cadastra categoria.          |
| `PUT`    | http://localhost:8080/categoria/1   | Atualiza categoria.          |
| `DELETE` | http://localhost:8080/categoria/1   | Exclui categoria pelo ID.    |

### Body:
######Exemplo de valores:

| `POST`   | http://localhost:8080/categoria     | Cadastra categoria.        |

```javascript
{
"descricao": "Banco de Dados"
}
```

| `PUT`    | http://localhost:8080/categoria/2   | Atualiza categoria.        |

```javascript
{
    "descricao": "Quality Assurance"
}
```
