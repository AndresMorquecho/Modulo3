import { FlatList,Dimensions, TouchableHighlight  } from "react-native";
import { StyleSheet, Text, View } from "react-native";
import { getGrades } from "../services/GradeServices";
import { FAB, ListItem, Avatar } from "@rneui/base";
import { useState } from "react";

export const ListGrades = ({ navigation }) => {
  const[time,setTime] = useState();
  
  const refreshList=()=>{
    setTime(new Date().getTime())
  }
  const screenWidth = Dimensions.get("window").width;    
  const ItemGrade = ({ nota }) => {
    return (
    
    <TouchableHighlight onPress={()=>{
        navigation.navigate("GradeFormNav",{notita: nota, refresh: refreshList});
    }}>
      <ListItem
        bottomDivider
        style={{
            justifyContent: "center",
            alignItems: "stretch",
            flex: 1,
            width: screenWidth * 0.9, 
        }}
      >
        <Avatar
          title={nota.subject.substring(0, 1)}
          containerStyle={{ backgroundColor: "#6733b9" }}
          rounded
        />

        <ListItem.Content>
          <ListItem.Title>{nota.subject}</ListItem.Title>
        </ListItem.Content>

        <ListItem.Content>
          <ListItem.Title>{nota.grade}</ListItem.Title>
        </ListItem.Content>

        <ListItem.Chevron></ListItem.Chevron>
      </ListItem>
    </TouchableHighlight>
  
    );
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={getGrades()}
        renderItem={({ item, index }) => {
          return <ItemGrade nota={item} indice={index} />;
        }}
        keyExtractor={(item) => {
          return item.subject;
        }}
        extraData={time}
      />

      <FAB
        title="+"
        placement="right"
        onPress={() => {
          navigation.navigate("GradeFormNav",{notita: null, refresh: refreshList});
        }}
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
});
