# Instruções

- Verifique se o JDK 8 está instalado em sua máquina. Rode o comando `javac -version` em seu terminal. A saída deve ser algo parecido com `javac 1.8.0_45-internal`.
- Primeiramente, rode o seguinte comando: `rmiregistry -J-Djava.rmi.server.codebase=file:/home/gabriel/galaga/out/production/galaga/`, adaptando o caminho do diretório para o equivalente na sua máquina. Evite espaços no caminho do diretório!
- Para rodar o Server, vá no IntelliJ, abra o diretório src/galaga/server, clique com o botão direito na classe Main, e depois clique em Run 'Server'.
- O servidor exibirá uma mensagem, 'Server Ready'.
- Para rodar o Cliente, vá no IntelliJ, abra o diretório src/galaga/client, clique com o botão direito na classe Main, e depois clique em Run 'Client'.
- Se quiser executar tudo novamente, é necessário reinicar o rmiregistry. Basta abortar sua execução (no terminal do Linux usa-se a combinação Ctrl-C para tal).
- Lembre-se também de fechar o Server no IntelliJ.

