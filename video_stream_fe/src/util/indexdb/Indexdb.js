

export default class IndexedDb {
    indexDbInstance;
    objectStoreName = 'VIDEO_STREAM';
    databaseName = "VIDEO_DATABASE";
    constructor() {
        let request = indexedDB.open(this.databaseName, 1);
        request.onupgradeneeded = (event) => {
            const db = event.target.result;
            if (!db.objectStoreNames.contains(this.objectStoreName)) {
                db.createObjectStore(this.objectStoreName, { keyPath: "id" });
            }
        };
        request.onsuccess = (event) => {
            this.indexDbInstance = event.target.result;
            console.log('Database opened successfully:', this.indexDbInstance);
        };

        request.onerror = (event) => {
            console.error('Error opening database:', event.target.error);
        };
    }

    static createInstance() {
        return new IndexedDb();
    }


    async addDataToObjectStore(incommingData) {
        let addedData = null;
        try {
            let dataStore = this.indexDbInstance.transaction([this.objectStoreName], "readwrite").objectStore(this.objectStoreName);
            addedData = await dataStore.add(incommingData);
        } catch (error) {
            console.log("error occured while adding data to object store:- ", error);
        }
        return addedData;
    }

}