//import { FlatList } from "react-native-gesture-handler";
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

let productos = [
    { nombre: "Laptop", categoria: "Tecnologia", precioCompra: 500, precioVenta: 750, id: 101 },
    { nombre: "Smartphone", categoria: "Tecnologia", precioCompra: 300, precioVenta: 500, id: 102 },
    { nombre: "Auriculares", categoria: "Tecnologia", precioCompra: 50, precioVenta: 80, id: 103 },
    { nombre: "Monitor", categoria: "Tecnologia", precioCompra: 150, precioVenta: 200, id: 104 },
    { nombre: "Impresora", categoria: "Oficina", precioCompra: 100, precioVenta: 150, id: 105 },
  ];
  

  export const getProductos= ()=>{
    return productos;
  }

  let ItemProducto =({item})=>{
    return(
        <View style={styles.lista}>
        
        
          <View justifyContent="center" marginHorizontal="10">
            <Text>{item.id}</Text>
          </View>
          <View style={styles.detallesProd}>
            <Text style={styles.txtNombreProd}> {item.nombre}</Text>
            <Text style={styles.txtcatProd}> {item.categoria}</Text>
          </View>
          <View style={styles.detallesPrecio}>
            <Text style={styles.txtPrecioVenta}>
              USD {item.precioVenta}
            </Text>
          </View>

      </View>
        


    )


  }

  export const ListaProductos=()=>{

    return(
        <FlatList

        data={productos}
        keyExtractor={(item)=> item.id}
        renderItem={({item,index})=>{
            return <ItemProducto  item = {item} indice = {index}
            
            /> 
        }}
        />
    )

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
  


  

    contNumeroProd: {
      flexDirection: "row",
      justifyContent: "flex-start",
      width: "100%",
      paddingLeft: 20,
      marginBottom: 10,
    },

  });
  