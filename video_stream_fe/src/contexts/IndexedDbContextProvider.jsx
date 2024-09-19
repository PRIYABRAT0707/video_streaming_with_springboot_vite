import React, { createContext } from 'react'
import IndexedDb from '../util/indexdb/Indexdb'

export const IndexedDbContex = createContext({})


const IndexedDbContextProvider = ({ children }) => {
    return (
        <IndexedDbContex.Provider value={IndexedDb.createInstance()}>
            {children}
        </IndexedDbContex.Provider>
    )
}

export default IndexedDbContextProvider
