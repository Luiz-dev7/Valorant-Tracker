# Valorant Tracker

Aplicativo mobile desenvolvido para acompanhar o desempenho do jogador no **Valorant**, permitindo
visualizar estatísticas recentes diretamente pelo celular.

---

## Sobre o Projeto

O **Valorant Tracker** utiliza a API pública de Henrik para coletar dados do jogador, respeitando o
limite de **20 requisições por minuto**.

O objetivo é fornecer uma forma simples e rápida de acompanhar seu desempenho nas partidas recentes,
além de outras informações relevantes do jogo.

---

## Funcionalidades


---

## Funcionalidades Futuras


---

## Estrutura de Páginas

O aplicativo será composto por pelo menos 3 telas principais:


- Campo para inserir o nome do jogador (Nick + Tag)
- Botão para buscar dados na API
- Validação de entrada

---

### 2. Tela de Perfil

- Nome do jogador
- Level / Rank
- Região
- Informações gerais da conta

---

### 3. Tela de Partidas

- Lista das últimas partidas
- Informações como:
    - Mapa jogado
    - Resultado (Vitória/Derrota)
    - KDA
    - Agente utilizado

---

### (Futuramente)  Tela de Notícias

- Atualizações recentes do jogo
- Novos agentes
- Mudanças de balanceamento

---

## Tecnologias Utilizadas

- Kotlin (Android)
- Retrofit (consumo de API)
- API pública do Henrik

---

## ️ Limitações

- API limitada a **20 requisições por minuto**
- Dependência de disponibilidade da API externa