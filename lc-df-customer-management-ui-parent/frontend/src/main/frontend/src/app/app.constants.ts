'use strict';

export const status = ['ACTIVE', 'SUSPENDED'];
export const category = ['BRONZE', 'SILVER', 'GOLD',  'PLATINUM'];

const springBootAppUrl = 'localhost:8080';
// export const springBootAppUrl = 'ldgrvtibadmr002.ladsys.net:8080';



// URL to web api
export const getAllCustomerUrl = `http://${springBootAppUrl}/customers`;
export const createCustomerUrl = `http://${springBootAppUrl}/customers/create`;
export const updateCustomerByIdUrl = `http://${springBootAppUrl}/customers/update`;
export const deleteCustomerByIdUrl = `http://${springBootAppUrl}/customers/delete`;
export const getCustomersByIdUrl = `http://${springBootAppUrl}/customers/get`;
export const getCustomersByFirstNameUrl = `http://${springBootAppUrl}/customers/get/firstname`;
export const getCustomersByLastNameUrl = `http://${springBootAppUrl}/customers/get/lastname`;
export const getCustomersByPostalCodeUrl = `http://${springBootAppUrl}/customers/get/postalcode`;
export const getCustomersByEmailIdUrl = `http://${springBootAppUrl}/customers/get/email`;
export const getCustomersByPhoneNoUrl = `http://${springBootAppUrl}/customers/get/phone`;
export const getCustomersByStatus = `http://${springBootAppUrl}/customers/get/status`;
export const getCustomersByCategory = `http://${springBootAppUrl}/customers/get/category`;
