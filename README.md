# Q2-Siemens
Desenvolva um programa que implementa e utiliza a função ConcatERemove(s,t,k).

## Testagem e Validação

O código foi desenvolvido com a sintaxe de JAVA. No arquivo enviado (a classe Conversor.java), já providenciei um método main pronto para execução. O método main estrutura a entrada conforme especificado no problema. Declaro três variáveis, cada uma correspondendo a uma linha da entrada e depois unifico a três em uma única entrada. Cada linha da entrada é representada por uma variável para facilitar a testagem.
```java
		String linha1 = "aba"; //String inicial
		String linha2 = "aba"; //String desejada
		String linha3 = "7"; //número de operações
		String input = linha1+"\n"+linha2+"\n"+linha3;
```
O problema indica limitações para os inputs. Para tal, criei o método verificarInput responsável para verificar se os parâmetros passados estão de acordo com as limitações do problema. Conforme o código presente no método main, apenas inputs com valores válidos levarão à execução da função ConcatERemove. Nesse sentido, para testagem da função, basta que os valores variáveis linha1, linha2 e linha3, presentes no método main, sejam alterados para valores diferentes, mas válidos conforme as limitações do problema. Para ignorar a validação dos valores, basta que o método main contenha apenas a chamada do método ConcatERemove com os parâmetros desejados.

## Comentários
A operação da função tem como base duas possibilidades gerais:

### Possibilidade 1 - número de movimentos permitidos (k) é maior ou igual que a soma dos tamanhos das duas strings (s e t). 
Nesse caso, a conversão de s em t sempre é possível, uma vez que é possível remover todos os chars de s e adicionar todos os chars de t, eliminando movimentos de sobra com o uso de remoções sobre uma string s vazia.
```java
		if (s.length()+t.length()<=k) {
			return "SIM";
		}
```
### Possibilidade 2 - k é menor que soma dos tamanhos de s e t. 
Nesse caso, é necessário avaliar se s e t possuem em comum alguma substring contínua iniciando no index 0. Quanto maior uma tal substring, menos movimentos serão necessários. Para buscar tal substring, a função itera por cada index da menor das duas strings (evitando uma tentativa de acesso a um index inexistente na menor string, mas existente na maior string), e, a cada iteração, compara os chars de s e t no index em questão. O laço opera até encontrar um index (armazenado em indexUltimoCharIgual) cujo próximo char em cada string é diferente um do ou outro, ou até iterar por cada index da menor string.
```java
		String menorString = (s.length()>=t.length())?t:s;
		int indexUltimoCharIgual=-1;
		for(int i = 0; i<menorString.length(); i++) {
			if(s.charAt(i)!=t.charAt(i)) {
				break;
			}
			indexUltimoCharIgual=i;
		}	}
```
Terminado o laço, o método prossege para avaliar se é possível realizar a conversão da string s em t. Para tal conversão ser possível, é necessário determinar quantos chars devem ser removidos e concatenados, e também quantos movimentos sobram após as remoções e concatenações necessárias (o problema exige que o exato número de movimentos seja utilizado, nem mais e nem menos). Para deixar o código mais legível, estes três valores são especificados com variáveis próprias. A expressão (indexUltimoCharIgual+1) representa o tamanho da substring contínua que s e t compartilham. Deste modo, a quantidade de remoções a serem feitas é o que resta da subtração do tamanho da string original (s) e a substring compartilhada, ao passo que a quantidade de adições é a subtração entre o tamanho de t e a substring comum. Por fim, a quantidade de movimentos restantes é o que sobra de k após o total de concatenações e remoções ser removido do valor inicial de k.
```java
		int totalRemove = s.length() - (indexUltimoCharIgual+1);
		int totalConcat = t.length() - (indexUltimoCharIgual+1);
		int movimentosSobra = k-(totalConcat+totalRemove);
```
Para determinar o retorno final, a última operação que a função deve realizar é verificar dois elementos. Primeiramente, a quantidade de movimentos restantes tem que que ser um valor positivo (ou seja, tem que ser verdade que (remoções+concatenações)<=k). Em segundo lugar a quantidade restante tem que ser par. Se for par, isso indica que é possível utilizar remoções/concatenações para eliminar os movimentos restantes sem que, ao final, a string transforma seja alterada. Caso a quantidade seja ímpar, a eliminação dos movimentos extras não é possível sem que a string final tenha um char a mais ou a menos do que o necessário, uma vez que sempre sobrará um movimento de remoção ou concatenação que não poderá ser compensado para transformação de s em t.

```java
		int totalRemove = s.length() - (indexUltimoCharIgual+1);
		int totalConcat = t.length() - (indexUltimoCharIgual+1);
		int movimentosSobra = k-(totalConcat+totalRemove);
		String retorno = ((movimentosSobra%2==0)&&(movimentosSobra>=0))?"SIM":"NÃO";
		return retorno;
```
