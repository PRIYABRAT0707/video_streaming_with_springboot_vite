// const dataArray=[{id:1,value:"ppanda",name:"bdk"},{id:2,value:"ppanda12",name:"bbsr"},{id:3,value:"ppanda56",name:"ctc"}]

export const globalSearch = (dataArray, searchParam) => {
    try {
        return Array.isArray(dataArray) ? dataArray?.filter((item) => {
            return Object?.values(item)?.some((data) => {
                return data?.toString().trim()?.toLowerCase()?.includes(searchParam?.toString()?.trim()?.toLowerCase())
            })
        }) : []
    } catch (error) {
        console.log("unable to serach element due to this:- ", error);
        return Array.isArray(dataArray) ? dataArray : [];
    }

}
