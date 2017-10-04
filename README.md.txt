1. Identifica��o:
 Otavio Augusto Ferreira Rodrigues 201276030 Sistemas de informa��o

2. Descri��o:
	Esse sistema foi feito para controlar os pedidos de um resturante e calcular os valor das comandas no fechamento delas.
	O sistema tamb�m controla o status das mesas (Aberto, Fechado), uma mesa aberta pode abrir comandas onde s�o guardados os pedidos nela inseridos,
	� poss�vel adicionar e excluir itens da comanda, enquanto ela estiver aberta. Ap�s o fechamento das comandas e da mesa � emitido um relat�rio com
	todos os itens que foram pedidos e o valor total dos gastos na mesa.

3. Modelo de dados utilizado;



4. Constru��o das telas;
	O sistema todo t�m tr�s telas, que s�o "Janela","JanelaFechamento","JanelaComanda";
	
	Na janela Principal h� duas listagens (List), onde pode-se encontrar a lista de mesas dispon�veis e a lista de comandas abertas para cada mesa.
	Nessa tela tamb�m existem cinco bot�es: 
	- Abrir Mesa;
	- Fechar Mesa;
	- Abrir Comanda
	- Fechar Comanda
	- Fazer Pedido;
	
	Ao clicar em "Fazer Pedido", estando uma comanda selecionada, abre-se uma tela onde podem ser adicionados os itens � comanda.
	Essa tela tem os Bot�es de adicionar e remover itens, um comboBox com a lista de itens dispon�veis, um campo para escolher a quantidade.
	E tamb�m uma tabela onde podem ser visualizados os produtos que foram adicionados a comanda.
	
	A terceira tela, de fechamento, fica vis�vel ao clicar em "Fechar Mesa", ela mostra todos os itens que foram registrados e tamb�m a soma
	dos valores dos pedidos da mesa.
	
5. Descri��o sucinta dos pontos importantes do funcionamento da interface;
	
	Para o funcionamento ideal da interface, deve-se para abrir uma comanda de uma mesa, ela deve estar aberta.
	Para fazer pedidos em uma comanda, ela tamb�m deve estar aberta e selecionada.
	Para o fechamento da mesa todas as comandas devem estar fechadas.

6. Discuss�o dos pontos que apresentaram maior dificuldade de implementa��o;
	As dificuldades enfrentadas na realiza��o desse trabalho foram:
	- Adapta��o as particularidades da linguagem na qual n�o estou habituado a programar;
	- Entendimento do funcionamento de certos elementos utilizados como o ComboBox;
	- Dificuldade grande em Montar um bom Layout.
