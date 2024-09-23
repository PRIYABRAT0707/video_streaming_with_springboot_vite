import React from "react"
import CustomCarousel from "./components/carousel/CustomCarousel"
import UseIndexedDbContex from "./hooks/UseIndexedDbContex"
import { Box, Button } from "@mui/material";
import VideoDocViewer from "./components/docviewer/VideoDocViewer";

function App() {
  const indexedDbInstance = UseIndexedDbContex();
  const onAddData = async () => {
    let savedData = await indexedDbInstance.addDataToObjectStore({ "id": 1, "name": "ppanda" })
    console.log(savedData);

  }
  console.log(indexedDbInstance);


  return (
    <>
      <Box sx={{height:"500px",p:1}}>
        <VideoDocViewer />
      </Box>
    </>
  )
}

export default App
