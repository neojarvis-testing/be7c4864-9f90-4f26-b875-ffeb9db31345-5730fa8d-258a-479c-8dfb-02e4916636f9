import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './ViewColleges.css'
const ViewColleges = ({token}) => {
 
   const [loading,setLoading] = useState(false);
   const [data,datas] = useState([]);
  
useEffect(()=> {
const fetchData = async () => {
    
    setLoading(true);
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      const response = axios.get(`${baseUrl}/api/colleges`,formData,{ headers });
      setData(response.data);
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
};
fetchData();
},[]);
 
return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>View College</h2> 
        {loading?(
            <p>Loading</p>
        ) : datas.length ===0 ? (
            <p>No Data Found</p>
        ) :
        (

            <table>
            <thead>
                <tr>
                    <th>College Id</th>
                    <th>College Name</th> 
                </tr>
            </thead>
            <tbody>
            <tr>
                    <th>{data.collegeId}</th>
                    <th>{data.collegeName}</th> 
                </tr>
            </tbody>
        </table>


        )
    }
    

    </div>
   </div>

);

};

export default ViewColleges;
>>>>>>> 31f5b23b19564ba6766de4f13bc885fe42bc3612
