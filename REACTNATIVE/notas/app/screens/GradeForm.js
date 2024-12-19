import { StyleSheet, Text, View } from "react-native";
import { Input, Button } from "@rneui/base";
import { useState } from "react";
import{saveGrades, updateGrade} from '../services/GradeServices'


export const GradeForm = ({navigation,route}) => {

  let isNew = true;
  let subjectR;
  let gradeR;

  if(route.params.notita != null){
    isNew = false;
  }

  if(! isNew){
    subjectR = route.params.notita.subject
    gradeR = route.params.notita.grade
  }

  const [subject, setSubject] = useState(subjectR);
  const [grade, setgrade] = useState(gradeR == null ? null : gradeR+ "");
  const [errorSubject, seterrorSubject] = useState();
  const [errorGrade, seterrorGrade] = useState();
  let hasErrors=false;




  const save=()=>{
    seterrorGrade(null);
    seterrorSubject(null);
    validate();

    if(!hasErrors){
      if(isNew){
        saveGrades({subject: subject, grade: grade });
        navigation.goBack();
      }else{
        updateGrade({subject: subject, grade: grade });
        route.params.refresh();
        navigation.goBack();

      }
    }


  }

  const validate=()=>{
    if(subject == null || subject ==""){
        seterrorSubject("Debe ingresar una materia")
        hasErrors = true;
    }

    let gradeFloat = parseFloat(grade);

    if(gradeFloat == null || isNaN(gradeFloat) || (gradeFloat <0 || gradeFloat>10) ){
        seterrorGrade("Debe ingresar una nota entre 0 y 10")
        hasErrors = true;

    }

  
  }

  return (
    <View style={styles.container}>
      <Text>Grados</Text>
      <Input
        value={subject}
        onChangeText={setSubject}
        placeholder="Ejemplo: Literatura"
        label="Materia"
        errorMessage={errorSubject}
        disabled={!isNew}
      />

      <Input
        value={grade}
        onChangeText={setgrade}
        placeholder="0-10"
        label="Nota"
        errorMessage={errorGrade}

      />

    <Button

        title="Guardar"
        icon={{
            name: 'save',
            type: 'entypo',
            color: 'white'
        }}

        buttonStyle={styles.saveButton}

        onPress={save}
    />

    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },

  saveButton:{
    backgroundColor: 'lightblue',
    borderRadius: 6
  }
});


