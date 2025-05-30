import React,{useEffect, useState} from 'react'; 
import axios from 'axios';
import {baseUrl} from '../apiConfig' 
import './StudentMyFeedback.css'


const StudentMyFeedback = ({token,userId}) => {
 
   const [loading,setLoading] = useState(false);
   const [datas,setData] = useState([]);
  
useEffect(()=> {
const fetchData = async () => {
    
    setLoading(true);
    try{
        const headers = {
            "Authorization":`Bearer ${token}`,
            "Content-Type":"application/json"
        }
      const response = await axios.get(`${baseUrl}/api/feedback/user/${userId}`,{ headers });
      setData(response.data);
    }catch(error){
        alert("Failed to Fetch Data:"+error);
    }
    finally{
        setLoading(false);
    }
};

//fetch 
fetchData();
},[token,userId]);
 
return (

    <div className='form-container'>
    <div className='form-box'>
        <h2>View Feedback Page</h2> 
        {loading?(
            <p>Loading</p>
        ) : datas.length === 0 ? (
            <p>No Data Found</p>
        ) :
        (

            <table>
            <thead> 
                <th>Feedback Id</th>
                    <th>User Id</th>
                <th>Date</th>
                    <th>Feedback Text</th>

            </thead>
            <tbody>

                {datas
              //  .filter(val=>val.userId===userId)
                .map((data) => (
                    <tr>
                    <td>{data.feedbackId}</td>
                    <td>{data.userId}</td>  
                    <td>{data.date}</td>  
                    <td>{data.feedbackText}</td>  
                    </tr>
                ))
                }    
            </tbody>
        </table>


        )
    }
    

    </div>
   </div>

);

};

export default StudentMyFeedback;