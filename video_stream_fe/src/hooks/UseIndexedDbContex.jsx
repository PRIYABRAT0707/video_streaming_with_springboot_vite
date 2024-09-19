import React, { useContext } from 'react'
import { IndexedDbContex } from '../contexts/IndexedDbContextProvider'

const UseIndexedDbContex =() => {
    return useContext(IndexedDbContex)
}

export default UseIndexedDbContex
