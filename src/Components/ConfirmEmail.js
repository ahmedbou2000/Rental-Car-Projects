import { useParams } from "react-router-dom";
import CryptoJS from "crypto-js";
import { useEffect,useState } from "react";

const HandleDecrypt = (encryptedData) => {
  const encryptionKey =
    "AMKDMA3206KJDA2792ENLKJL&2DZOHADB83027302EOOY3E2ORY082324R2IRJ028U402"; // Replace with your encryption key
  const decryptionMethod = "AES-256-CBC";

  // Decode the base64-encoded string
  const decodedData = CryptoJS.enc.Base64.parse(encryptedData);

  // Get the IV (initialization vector) from the decoded data
  const iv = CryptoJS.lib.WordArray.create(decodedData.words.slice(0, 4));

  // Get the encrypted data from the decoded data
  const encryptedBytes = CryptoJS.lib.WordArray.create(
    decodedData.words.slice(4)
  );

  // Decrypt the encrypted data
  const decryptedBytes = CryptoJS.AES.decrypt(
    { ciphertext: encryptedBytes },
    CryptoJS.enc.Utf8.parse(encryptionKey),
    { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 }
  );

  // Convert the decrypted bytes to a string
  console.log("decrypted", decryptedBytes.toString(CryptoJS.enc.Utf8));
  return decryptedBytes.toString(CryptoJS.enc.Utf8);
};


function isWithinOneDay(date1, date2) {
  const oneDayMilliseconds = 24 * 60 * 60 * 1000; // Number of milliseconds in a day
  const difference = Math.abs(date1.getTime() - date2.getTime());

  return difference <= oneDayMilliseconds;
}
const ConfirmEmail = () => {
  let { email, date } = useParams();
  const [isValid ,setIsValid] = useState(false);
  useEffect(()=>{
    console.log("date",date)
    const data = HandleDecrypt(date);
    console.log("data",data)
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0");
    const day = String(currentDate.getDate()).padStart(2, "0");
    const formattedDate = `${year}-${month}-${day}`;

    if(isWithinOneDay(new Date(formattedDate),new Date(data))){
        setIsValid(true);
    }
  },[])
  return (
    <>
      <p>confirmation page {isValid?"yes":"Nooo"}</p>
    </>
  );
};

export default ConfirmEmail;
