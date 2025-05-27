import React, { useState } from 'react'
import axios from 'axios';
import {baseUrl} from '../apiConfig' 

const LoanForm = ({token}) => {
  const [formData, setFormData] = useState(
    {
      loanType: '',
      description: '',
      interestRate: '',
      maximumAmount: '',
      minimumAmount: '',
      minimumTenure: '',
      processingFee: '',
      prepaymentPenalty: '',
      gracePeriod: '',
      latePaymentFee: '',
    }
  );


  const [errors, setErrors] = useState(
    {
      loanType: '',
      description: '',
      interestRate: '',
      maximumAmount: '',
      minimumAmount: '',
      minimumTenure: '',
      processingFee: '',
      prepaymentPenalty: '',
      gracePeriod: '',
      latePaymentFee: '',
    }
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData, [name]: value
    }))

    if (errors[name]) {
      setErrors((prevError) => ({
        ...prevError,
        [name]: undefined
      }))
    }
  }


  const validateForm = () => {
    let newErrors = {};
    if (!formData.loanType.trim()) {
      newErrors.loanType = "Loan type is required"
    }
    if (!formData.description.trim()) {
      newErrors.description = "Description is required"
    }

    setErrors(newErrors)
    return Object.keys(newErrors).length === 0;
  }

  const [check, setCheck] = useState(false);

  const handleSubmit = (e) => {

    e.preventDefault();
    setCheck(true)
    const isValid = validateForm();
    if(isValid){
      // Call API 
  const headers = {
  "Authorization":`Bearer ${token}`,
  "Content-Type":"application/json"
  }
      axios.post(`${baseUrl}/api/loans`,formData,{ headers })
      .then((response)=>{
          const {loanId} = response.data; //Loan
          alert("Success !"+loanId);
      }).catch((error)=>{
          let message = "";
          alert(error);
          if(error?.response?.data.status=="400"){
              message = error?.response?.data.message ||"Failed";
          }else{
          message = error?.response?.data.status || "Failed";}
   
      }).finally(()=>{
          setLoading(false);
      });
  }else{
      // logic
      alert("Validation Error !");
  }

  }

  return (
    <div className='form-container'>

      {check ? <div>Loan type is required Description is required</div> : "" }
      

      <form className='form-box' onSubmit={handleSubmit}>
        <h2>Create New Loan</h2>
        <input type='text' name='loanType' placeholder='Loan Type' value={formData.loanType} onChange={handleChange} />
        {errors.loanType && <div>{errors.name}</div>}
        <input type='text' name='description' placeholder='Description' value={formData.description} onChange={handleChange} />
        {errors.description && <div>{errors.name}</div>}
        <input type='text' name='interestRate' placeholder='Interest Rate' value={formData.name} onChange={handleChange} />
        {errors.interestRate && <div>{errors.name}</div>}
        <input type='text' name='maximumAmount' placeholder='Maximum Amount' value={formData.name} onChange={handleChange} />
        {errors.maximumAmount && <div>{errors.name}</div>}
        <input type='text' name='minimumAmount' placeholder='Minimum Amount' value={formData.name} onChange={handleChange} />
        {errors.minimumAmount && <div>{errors.name}</div>}
        <input type='text' name='minimumTenure' placeholder='Minimum Tenure' value={formData.name} onChange={handleChange} />
        {errors.minimumTenure && <div>{errors.name}</div>}
        <input type='text' name='processingFee' placeholder='Processing Fee' value={formData.name} onChange={handleChange} />
        {errors.processingFee && <div>{errors.name}</div>}
        <input type='text' name='prepaymentPenalty' placeholder='Prepayment Penalty' value={formData.name} onChange={handleChange} />
        {errors.prepaymentPenalty && <div>{errors.name}</div>}
        <input type='text' name='gracePeriod' placeholder='Grace Period' value={formData.name} onChange={handleChange} />
        {errors.gracePeriod && <div>{errors.name}</div>}
        <input type='text' name='latePaymentFee' placeholder='Late Payment Fee' value={formData.name} onChange={handleChange} />
        {errors.latePaymentFee && <div>{errors.name}</div>}

        <button type='submit' name="Add Loan" role='button'>Add Loan</button>
      </form>
    </div>

  )
}

export default LoanForm;