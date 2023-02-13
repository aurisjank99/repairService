# Description

-------

Swagger:
http://localhost:8080/swagger-ui/index.html#/
or
http://localhost:8080/

-------
The role of the service: validation work orders – Analysis, Repair, Replacement. Each work order contains
collection of parts.
Technical requirements
1. The service shall expose a REST interface consuming work orders in JSON format and returning validation result
   to the client
2. Service shall be flexible to extend the validation logic in terms of:
- new business validation rules
- new types to be supported
3. Service shall store all validation requests history in DB:
- date of receiving request
- type of work order
- department
- status: valid/not valid
4. Service shall have UI parts:
- fill JSON request and check validation result
- get all validation requests history
  Business requirements
  The following validation rules shall be implemented:
  • For all work orders
  department: not empty and in the valid department list
  start date: not empty and before current date
  end date: not empty and after start date
  currency: valid ISO code (ISO 4217)
  cost: greater than 0
  • Repair
  analysis date: after start date and before end date
  responsible person: not empty
  test date: not empty, after analysis date and before end date
  parts: total count greater than 0
  • Replacement
  factory name: not empty
  factory order number: the length is 10, first 2 characters - letters, others – numbers.
  parts: all inventory numbers are not empty
  In case of multiple errors - all of them should be in response