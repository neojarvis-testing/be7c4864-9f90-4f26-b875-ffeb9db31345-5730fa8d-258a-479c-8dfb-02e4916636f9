import React, { useState } from 'react'
import axios from 'axios';
import {baseUrl} from '../apiConfig'
import './LoanForm.css'

const LoanForm = ({token}) => {
  const [loading,setLoading] = useState(false);
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
      maxAmount: '',
      minAmount: '',
      minTenureMonths: '',
      maxTenureMonths: '',
      processingFee: '',
      prepaymentPenalty: '',
      gracePeriodMonths: '',
      latePaymentFee: '',
      status: '',
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

    if (isValid) {
 
      // Call API 
  const headers = {
    "Authorization":`Bearer ${token}`,
    "Content-Type":"application/json"
  }
      axios.post(`${baseUrl}/api/loans`,formData,{ headers })
      .then((response)=>{
          const {loanId} = response.data; 
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
      

      <form className='form-box' onSubmit={handleSubmit}>
        <h2>Create New Loan</h2>
  
      {errors.loanType && <div>{errors.loanType}</div>}
        <input type='text' name='loanType' placeholder='Loan Type' value={formData.loanType} onChange={handleChange} />
       
        {errors.description && <div>{errors.description}</div>}
        <input type='text' name='description' placeholder='Description' value={formData.description} onChange={handleChange} />
        

        <input type='text' name='interestRate' placeholder='Interest Rate' value={formData.interestRate} onChange={handleChange} />
        {errors.interestRate && <div>{errors.interestRate}</div>}

        {errors.maxAmount && <div>{errors.maxAmount}</div>}
        <input type='text' name='maxAmount' placeholder='Maximum Amount' value={formData.maxAmount} onChange={handleChange} />

        {errors.minAmount && <div>{errors.minAmount}</div>}
        <input type='text' name='minAmount' placeholder='Minimum Amount' value={formData.minAmount} onChange={handleChange} />
        
        {errors.minTenureMonths && <div>{errors.minTenureMonths}</div>}
        <input type='text' name='minTenureMonths' placeholder='Minimum Tenure' value={formData.minTenureMonths} onChange={handleChange} />

        {errors.maxTenureMonths && <div>{errors.maxTenureMonths}</div>}
        <input type='text' name='maxTenureMonths' placeholder='Processing Fee' value={formData.maxTenureMonths} onChange={handleChange} />

        {errors.prepaymentPenalty && <div>{errors.prepaymentPenalty}</div>}
        <input type='text' name='prepaymentPenalty' placeholder='Prepayment Penalty' value={formData.prepaymentPenalty} onChange={handleChange} />

        {errors.processingFee && <div>{errors.processingFee}</div>}
        <input type='text' name='processingFee' placeholder='processing Fee' value={formData.processingFee} onChange={handleChange} />

        {errors.gracePeriodMonths && <div>{errors.gracePeriodMonths}</div>}
        <input type='text' name='gracePeriodMonths' placeholder='Late Payment Fee' value={formData.gracePeriodMonths} onChange={handleChange} />
        
        {errors.latePaymentFee && <div>{errors.latePaymentFee}</div>}
        <input type='text' name='latePaymentFee' placeholder='Late Payment Fee' value={formData.latePaymentFee} onChange={handleChange} />

        {errors.status && <div>{errors.status}</div>}
        <input type='hidden' name='status' placeholder='Late Payment Fee' value="Active" onChange={handleChange} />
        
        <button type='submit'  name="Add Loan" role='button' disabled={loading}>
        {loading ? 'Logging in...' : 'Add Loan'}
        </button>
      </form>
    </div>

  )
}

export default LoanForm;