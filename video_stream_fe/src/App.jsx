import React from "react"
import CustomCarousel from "./components/carousel/CustomCarousel"
import UseIndexedDbContex from "./hooks/UseIndexedDbContex"
import { Box, Button } from "@mui/material";
import VideoDocViewer from "./components/docviewer/VideoDocViewer";
import { globalSearch } from "./util/search/search";


const dataArray=[{id:1,value:"ppanda",name:"bdk"},{id:2,value:"ppanda12",name:"bbsr"},{id:3,value:"ppanda56",name:"ctc"}]
function App() {
  const indexedDbInstance = UseIndexedDbContex();
  const onAddData = async () => {
    let savedData = await indexedDbInstance.addDataToObjectStore({ "id": 1, "name": "ppanda" })
    console.log(savedData);

  }
  console.log(indexedDbInstance);

  console.log("global search:-",globalSearch(dataArray,12));
  

  return (
    <>
      <Box sx={{height:"500px",p:1}}>
        <VideoDocViewer />
      </Box>
    </>
  )
}

export default App
