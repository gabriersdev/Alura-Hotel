-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 08-Fev-2023 às 14:39
-- Versão do servidor: 8.0.30
-- versão do PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hotel_alura`

CREATE DATABASE IF NOT EXISTS hotel_alura CHARACTER SET utf8mb4 CHARSET utf8mb4;
USE hotel_alura;

--

-- --------------------------------------------------------

--
-- Estrutura da tabela `hospedes`
--
-- Criação: 06-Fev-2023 às 16:02
-- Última actualização: 08-Fev-2023 às 13:06
--

CREATE TABLE `hospedes` (
  `id_hospede` int NOT NULL,
  `nome_hospede` varchar(80) NOT NULL,
  `sobrenome_hospede` varchar(50) NOT NULL,
  `data_nascimento_hospede` date NOT NULL,
  `nacionalidade_hospede` varchar(60) NOT NULL,
  `telefone_hospede` varchar(14) NOT NULL,
  `cod_reserva_hospede` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `reservas`
--
-- Criação: 03-Fev-2023 às 14:15
-- Última actualização: 08-Fev-2023 às 13:06
--

CREATE TABLE `reservas` (
  `id_reserva` int NOT NULL,
  `data_entrada_reserva` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_saida_reserva` datetime NOT NULL,
  `valor_saida_reserva` decimal(10,2) NOT NULL DEFAULT '0.00',
  `forma_pagamento_reserva` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--
-- Criação: 31-Jan-2023 às 20:09
-- Última actualização: 06-Fev-2023 às 18:33
--

CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL,
  `nome_usuario` varchar(50) NOT NULL,
  `senha_usuario` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nome_usuario`, `senha_usuario`) VALUES
(1, 'admin', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `hospedes`
--
ALTER TABLE `hospedes`
  ADD PRIMARY KEY (`id_hospede`),
  ADD KEY `cod_reserva_hospede` (`cod_reserva_hospede`);

--
-- Índices para tabela `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `hospedes`
--
ALTER TABLE `hospedes`
  MODIFY `id_hospede` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `hospedes`
--
ALTER TABLE `hospedes`
  ADD CONSTRAINT `hospedes_ibfk_1` FOREIGN KEY (`cod_reserva_hospede`) REFERENCES `reservas` (`id_reserva`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
