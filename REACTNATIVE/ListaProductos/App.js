import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import {
  StyleSheet,
  Text,
  View,
  FlatList,
  VirtualizedList,
  TextInput,
  ScrollView,
  Button,
  Alert,
  TouchableOpacity,
  Modal,
} from "react-native";

let indiceSeleccionado = -1;
let productos = [
  {
    nombre: "Laptop",
    categoria: "Tecnologia",
    precioCompra: 500,
    precioVenta: 750,
    id: 101,
  },
  {
    nombre: "Smartphone",
    categoria: "Tecnologia",
    precioCompra: 300,
    precioVenta: 500,
    id: 102,
  },
  {
    nombre: "Auriculares",
    categoria: "Tecnologia",
    precioCompra: 50,
    precioVenta: 80,
    id: 103,
  },
  {
    nombre: "Monitor",
    categoria: "Tecnologia",
    precioCompra: 150,
    precioVenta: 200,
    id: 104,
  },
  {
    nombre: "Impresora",
    categoria: "Oficina",
    precioCompra: 100,
    precioVenta: 150,
    id: 105,
  }, // corregido
];

let esNuevo = true;

export default function App() {
  const [txtNombre, setTxtNombre] = useState("");
  const [txtCodigo, setTxtCodigo] = useState();
  const [txtCategoria, setTXTCategoria] = useState("");
  const [txtPrecioCompra, setTxtPrecioCompra] = useState("");
  const [txtPrecioVenta, setTxtPrecioVenta] = useState("");
  const [modal, setmodal] = useState(false);

  const [txtNproductos, setTxtNProductos] = useState(productos.length);

  let guadarProducto = () => {
    let existeProducto = () => {
      for (let i = 0; i < productos.length; i++) {
        if (productos[i].id == txtCodigo) {
          return true;
        }
      }

      return false;
    };

    if (
      txtCodigo === "" ||
      txtNombre === "" ||
      txtCategoria === "" ||
      txtPrecioCompra === "" ||
      txtPrecioVenta === ""
    ) {
      Alert.alert("Atención", "Asegurese de ingresar todos los campos");
    } else {
      if (esNuevo) {
        if (existeProducto()) {
          Alert.alert(
            "Ya existe un producto registrado con el codigo: " + txtCodigo
          );
        } else {
          let nuevoProducto = {
            nombre: txtNombre,
            categoria: txtCategoria,
            precioCompra: txtPrecioCompra,
            precioVenta: txtPrecioVenta,
            id: txtCodigo,
          };
          productos.push(nuevoProducto);
          limpiar();
        }
      } else {
        productos[indiceSeleccionado].nombre = txtNombre;
        productos[indiceSeleccionado].categoria = txtCategoria;
        productos[indiceSeleccionado].precioCompra = txtPrecioCompra;
        limpiar();
      }
    }
  };

  let limpiar = () => {
    setTxtNombre("");
    setTxtCodigo("");
    setTXTCategoria("");
    setTxtPrecioCompra("");
    setTxtPrecioVenta("");
    esNuevo = true;
  };

  let ItemProducto = (props) => {
    return (
      <View style={styles.lista}>
        <TouchableOpacity
          style={styles.bton}
          onPress={() => {
            console.log(props.item);
            setTxtPrecioCompra(props.item.precioCompra.toString());
            setTxtPrecioVenta(props.item.precioVenta.toString());
            setTxtCodigo(props.item.id.toString());
            setTxtNombre(props.item.nombre);
            setTXTCategoria(props.item.categoria);
            indiceSeleccionado = props.indice;
            esNuevo = false;
          }}
        >
          <View justifyContent="center" marginHorizontal="10">
            <Text>{props.item.id}</Text>
          </View>
          <View style={styles.detallesProd}>
            <Text style={styles.txtNombreProd}> {props.item.nombre}</Text>
            <Text style={styles.txtcatProd}> {props.item.categoria}</Text>
          </View>
          <View style={styles.detallesPrecio}>
            <Text style={styles.txtPrecioVenta}>
              USD {props.item.precioVenta}
            </Text>
          </View>
        </TouchableOpacity>

        <View style={styles.contAcciones}>
          <Button
            title="X"
            color="#bc4749"
            onPress={() => {
              indiceSeleccionado = props.indice
              setmodal(true);
            }}
          ></Button>
        </View>
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <View>
        <Text style={styles.titulo}>Productos</Text>
      </View>

      <View style={styles.contTXT}>
        <ScrollView>
          <TextInput
            style={styles.cajasTexto}
            placeholder="Codigo"
            value={txtCodigo}
            onChangeText={setTxtCodigo}
            keyboardType="numeric"
          />
          <TextInput
            style={styles.cajasTexto}
            placeholder="Nombre"
            value={txtNombre}
            onChangeText={setTxtNombre}
          />
          <TextInput
            style={styles.cajasTexto}
            placeholder="Categoria"
            value={txtCategoria}
            onChangeText={setTXTCategoria}
          />
          <TextInput
            style={styles.cajasTexto}
            placeholder="Precio de compra"
            keyboardType="numeric"
            value={txtPrecioCompra}
            onChangeText={(PC) => {
              if (PC > 0) {
                setTxtPrecioCompra(PC);
                let PrecioVenta = parseFloat(PC) * 1.2;
                setTxtPrecioVenta(PrecioVenta.toFixed(2));
              } else {
                setTxtPrecioCompra();
                setTxtPrecioVenta();
              }
            }}
          />
          <TextInput
            style={styles.cajasTexto}
            placeholder="Precio de venta"
            value={txtPrecioVenta}
            editable={false}
          />
        </ScrollView>
      </View>
      <View style={styles.contBotones}>
        <Button
          title="Guardar"
          onPress={() => {
            guadarProducto();
          }}
        ></Button>
        <Button
          title="Nuevo"
          onPress={() => {
            limpiar();
          }}
        ></Button>
      </View>

      <View style={styles.contNumeroProd}>
        <Text>N° Productos {txtNproductos} </Text>
      </View>

      <View style={styles.contenedorLista}>
        <FlatList
          data={productos}
          renderItem={(prod) => {
            return <ItemProducto item={prod.item} indice={prod.index} />;
          }}
          keyExtractor={(item) => {
            return item.id;
          }}
        />
      </View>

      <View style={styles.foot}>
        <Text>Desarrollado: Andres Morquecho</Text>
      </View>

      <Modal animationType="slide" transparent visible={modal}>
        <View
          style={{
            flex: 1,
            backgroundColor: "rgba(1,1,1,0.5)",
            justifyContent: "center",
            alignItems: "center",
            alignContent: "center",
            padding: 1,
          }}
        >
          <View
            style={{
              backgroundColor: "white",
              width: "80%",
              height: "30%",
              borderRadius: 8,
            }}
          >
            <View
              style={{
                flex: 1,
                justifyContent: "center",
                fontSize: 15,
                fontWeight: "bold",
              }}
            >
              <Text>¿Esta seguro de eliminar el item seleccionado?</Text>
            </View>

            <View
              style={{
                flexDirection: "row",
                justifyContent: "space-evenly",
                flex: 3,
                alignItems: "center",
                padding: 1,
              }}
            >
              <Button
                title="Eliminar"
                onPress={() => {

                  productos.splice(indiceSeleccionado, 1);
                  setTxtNProductos(productos.length);
                  setmodal(false)
                  limpiar();
            
                }}
              ></Button>
              <Button title="Cancelar" onPress={()=>{setmodal(false)}} ></Button>
            </View>
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "4a4e69",
    alignItems: "center",
    justifyContent: "center",
  },

  titulo: {
    fontSize: 22,
    marginTop: 80,
    fontWeight: "bold",
    marginBottom: 15,
  },

  contenedorLista: {
    //backgroundColor: 'red',
    flex: 1,
    flexDirection: "row",
    alignItems: "stretch",
  },

  lista: {
    //backgroundColor: 'blue',
    flexDirection: "row",
    marginBottom: 8,
    borderWidth: 1,
    marginHorizontal: 20,
    borderRadius: 6,
  },

  detallesProd: {
    //backgroundColor: 'lightblue',
    flex: 3,
  },

  detallesPrecio: {
    flex: 1,
    justifyContent: "center",
  },

  txtPrecioVenta: {
    fontSize: 15,
    fontWeight: "bold",
  },

  txtNombreProd: {
    fontSize: 17,
    fontWeight: "bold",
  },

  txtcatProd: {
    fontStyle: "italic",
  },

  contTXT: {
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "stretch",
    width: "100%",
    paddingHorizontal: 20,
  },

  cajasTexto: {
    borderWidth: 1,
    marginBottom: 10,
    borderRadius: 7,
  },

  contBotones: {
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-around",
    alignContent: "center",
    marginBottom: 20,
  },
  contAcciones: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around",
    marginLeft: 5,
    width: "100%",
  },

  foot: {
    flex: 0.1,
    backgroundColor: "lightblue",
    width: "100%",
    justifyContent: "center",
    fontWeight: "bold",
    alignItems: "flex-end",
    paddingHorizontal: 20,
  },
  contNumeroProd: {
    flexDirection: "row",
    justifyContent: "flex-start",
    width: "100%",
    paddingLeft: 20,
    marginBottom: 10,
  },

  bton: {
    backgroundColor: "lightblue",
    flexDirection: "row",
    flex: 7,
    width: "100%",
  },
});
