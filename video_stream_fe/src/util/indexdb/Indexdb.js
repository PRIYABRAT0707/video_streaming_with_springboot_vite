

export default class IndexedDb {
    indexDbInstance;
    objectStoreName = 'VIDEO_STREAM';
    userStoreName = 'USER_STREAM';
    productStoreName = 'PRODUCT_STREAM';
    databaseName = "VIDEO_DATABASE";
    constructor() {
        let request = indexedDB.open(this.databaseName, 1);
        request.onupgradeneeded = (event) => {
            const db = event.target.result;
            if (!db.objectStoreNames.contains(this.objectStoreName)) {
                db.createObjectStore(this.objectStoreName, { keyPath: 'id', autoIncrement: true });
            }
            if (!db.objectStoreNames.contains(this.userStoreName)) {
                db.createObjectStore(this.userStoreName, { keyPath: 'id', autoIncrement: true });
            }
            if (!db.objectStoreNames.contains(this.productStoreName)) {
                db.createObjectStore(this.productStoreName, { keyPath: 'id', autoIncrement: true });
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
    async retriveDataFromObjectStore() {
        let addedData = null;
        try {
            let dataStore = this.indexDbInstance.transaction([this.objectStoreName], 'readonly').objectStore(this.objectStoreName);
            addedData = await dataStore.getAll();
        } catch (error) {
            console.log("error occured while adding data to object store:- ", error);
        }
        return addedData;
    }

}