import { StatusBar } from "expo-status-bar";
import {
  StyleSheet,
  Text,
  View,
  FlatList,
  TextInput,
  Button,
  Alert,
} from "react-native";
import { useState } from "react";
let personas = [
  { nombre: "Andres", apellido: "Morquecho", cedula: "123456789" },
  { nombre: "Peter", apellido: "Parker", cedula: "123456799" },
  { nombre: "Juan", apellido: "Ramirez", cedula: "123456999" },
];


// Determinar si se esta creando una nueva persona o se esta modificando una existente
let esNuevo = true;

// esta variable almacena el indice del arreglo del elemento seleccionado para edicion
let indiceSeleccionado = -1;


export default function App() {
  const [txtCedula, setTxtCedula] = useState();
  const [txtapellido, setTxtApellido] = useState();
  const [txtnombre, setTxtNombre] = useState();
  const [numElemetos, setNumElementos] = useState(personas.length);

  let ItemPersona = (props) => {
    return (
      <View style={styles.itemPersona}>
        <View style={styles.areaIndice}>
          <Text> {props.indice} </Text>
        </View>
  
        <View style={styles.areaContenido}>
          <Text style={styles.textoPrincipal}>
            {props.persona.nombre} {props.persona.apellido}
          </Text>
          <Text>{props.persona.cedula}</Text>
        </View>
        <View style={styles.areabtonPersona}>
            <Button title="E" color="lightgreen" onPress={()=>{

  
              setTxtCedula(props.persona.cedula)
              setTxtNombre(props.persona.nombre)
              setTxtApellido(props.persona.apellido)
              indiceSeleccionado = (props.indice)
              esNuevo = false;

  
            }} /> 
            <Button title="X" color="red" onPress={()=>{

                indiceSeleccionado = (props.indice)
                personas.splice(indiceSeleccionado,1);
                setNumElementos(personas.length);
            }}/> 
  
        </View>
      </View>
    );
  };

  
  let guardarPerson = () => {

    if(esNuevo){
      if(existePersonas()){
        Alert.alert("Ya existe una persona con la cedula: " + txtCedula);
      }else{
        let persona = {
          nombre: txtnombre,
          apellido: txtapellido,
          cedula: txtCedula,
        };
        personas.push(persona);

      }
    }else{
      
      personas[indiceSeleccionado].nombre= txtnombre;
      personas[indiceSeleccionado].apellido = txtapellido;
      
    }
    limpiar();

    setNumElementos(personas.length)
  };

  let existePersonas=()=>{
    for(let i = 0; i< personas.length;i++){

      if(personas[i].cedula == txtCedula){
        return  true;
      }

    }
    return; false
  }

  let limpiar = () => {
    setTxtCedula("");
    setTxtApellido("");
    setTxtNombre("");
    esNuevo = true;

  };

  return (
    <View style={styles.container}>
      <View style={styles.head}>
        <Text style={styles.textoPrincipal}>Personas</Text>

        <TextInput
          value={txtCedula}
          placeholder="Ingrese su cedula"
          onChangeText={setTxtCedula}
          style={styles.txtDatos}
          keyboardType="numeric"
          editable={esNuevo}
        />
        <TextInput
          value={txtnombre}
          placeholder="Ingrese su nombre"
          onChangeText={setTxtNombre}
          style={styles.txtDatos}
        />
        <TextInput
          value={txtapellido}
          placeholder="Ingrese su apellido"
          onChangeText={setTxtApellido}
          style={styles.txtDatos}
        />
      </View>

      <View style={styles.areaBotones}>
        <Button
          title="Guardar"
          onPress={() => {
            guardarPerson();
          }}
        />
        <Button
          title="Nuevo"
          onPress={() => {
            limpiar();
          }}

          
        />
       
      </View>
      <View>
        <Text> Numero de personas:  {numElemetos}</Text>
      </View>
      <View style={styles.body}>
        <FlatList
          style={styles.list}
          data={personas}
          renderItem={elemento => {
            return (
              <ItemPersona persona={elemento.item} indice={elemento.index} />
            );
          }}
          keyExtractor={(item) =>  { return item.cedula}}
        />
      </View>

      <View style={styles.foot}>
        <Text>Autor: Andres Morquecho</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    //backgroundColor: "skyblue",
    paddingTop: 50,
    paddingHorizontal: 20,
  },
  list: {
    // backgroundColor: "darkseagreen",
    flex: 1,
  },
  itemPersona: {
    flexDirection: "row",
    //backgroundColor: "sandybrown",
    padding: 7,
    marginBottom: 10,
    borderWidth: 1,
    borderRadius: 6,
  },
  textoPrincipal: {
    fontSize: 15,
    fontWeight: "bold",
  },
  head: {
    marginTop: 40,
    flex: 4.5,
    justifyContent: "center",
    marginBottom: 15,
  },
  body: {
    flex: 6,

  },
  foot: {
    paddingHorizontal: 1,
    flex: 1,
    //backgroundColor: "gray",
    justifyContent: "center",
    alignItems: "flex-end",
  },

  areaIndice: {
    flex: 1,
    //backgroundColor: 'gray',
    justifyContent: "center",
    alignItems: "center",
    padding: 0.0001,
  },

  areaContenido: {
    paddingLeft: 10,
    flex: 9,
    backgroundColor: "#cae9ff",
    borderRadius: 6,
  },

  txtDatos: {
    borderWidth: 1,
    margin: 5,
    borderRadius: 7,
  },

  areaBotones: {
    flexDirection: "row",
    justifyContent: "space-around",
    alignItems: "center",
    alignContent: "center",
    marginBottom: 30,
    paddingInline: 1,
  },
  areabtonPersona:{
      flex: 2,
      flexDirection: "row",
      alignItems: "center",
      paddingInline: 1,
      justifyContent: 'space-around'
  }
});
