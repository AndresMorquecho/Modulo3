import { createIconSetFromFontello } from "react-native-vector-icons";

let grades = [{subject: 'Historia', grade: 10}, {subject: 'Fisica', grade: 6.5}];

export const saveGrades=(grade)=>{
    grades.push(grade);

}

export const getGrades =()=>{
    return  grades;
}



export const updateGrade = (nota) => {
    const index = grades.findIndex((item) => item.subject === nota.subject);
    if (index !== -1) {

      grades[index] = { ...grades[index], grade: nota.grade };
    } else {
      console.log(`Nota con materia ${nota.subject} no encontrada.`);
    }

        console.log({grades})
  };
  

const find= (subject)=>{

    for(let i = 0; i < grades.length; i++){

        if(grades[i].subject == subject){
            return grades[i];
        }

    }

    return null;

}