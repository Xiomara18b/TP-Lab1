<?php
$servername = "localhost";
$username = "root"; // Cambia esto si tienes un usuario diferente
$password = ""; // Cambia esto si tienes una contraseña
$dbname = "olympics";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$sql = "SELECT * FROM medals";
$result = $conn->query($sql);

$medals = array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $medals[] = $row;
    }
}

echo json_encode($medals);

$conn->close();
?>
