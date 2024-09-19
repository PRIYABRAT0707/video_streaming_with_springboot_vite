import React from "react"
import CustomCarousel from "./components/carousel/CustomCarousel"
import UseIndexedDbContex from "./hooks/UseIndexedDbContex"
import { Button } from "@mui/material";

function App() {
  const indexedDbInstance = UseIndexedDbContex();
  const onAddData = async () => {
      let savedData = await indexedDbInstance.addDataToObjectStore({ "id": 1, "name": "ppanda" })
      console.log(savedData);
    
  }
console.log(indexedDbInstance);


  return (
    <>
      <Button onClick={onAddData} variant="contained">add data</Button>
      <CustomCarousel />
    </>
  )
}

export default App
