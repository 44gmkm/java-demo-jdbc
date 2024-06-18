INSERT INTO `barcos` (`nombre`, `eslora`, `potencia_cv`, `potencia_kw`, `es_extrangero`) VALUES
('Barco de Jose', 12.0, 60.0, 80.460, false),
('Barco 1234', 210, 91000.0, 68000.0, true),
('Prueba', 132, 54000.0, 73000.0, true),
('Ximomar', 10.0, 43.0, 32.1, false);

INSERT INTO `tripulantes` (`nombre`, `apellido1`, `apellido2`, `rol`, `barco`) VALUES
('Jose', 'Garcia', 'Vazquez', 'capitan', 1),
('Prueba', 'Lopez', '','capitan', 2),
('Chema', 'Gandia', '', 'jefe de maquinas', 2),
('Cristina', 'Fasay', 'Campohermoso', 'capitan', 3),
('Joaquim', 'Balmain', 'Ramirez', 'cocinero', 4);

INSERT INTO `demo` (`float`, `varchar`, `bool`, `char`, `date`) VALUES
(11.3, 'sepia', true, 'x', '2025-02-04'),
(253.3, 'mesa', false, 'z', '1987-03-05'),
(87.3, 'cafe', true, 'f', '2025-12-12'),
(4.5, 'amigo', false, 'c', '2002-01-07');