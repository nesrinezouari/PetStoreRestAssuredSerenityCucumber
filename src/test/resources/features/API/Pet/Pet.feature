Feature: As Customer I would To get Pets with status Filter , Create Update and Delete New Pet 

  @PostPetwithfiledata
  Scenario Outline: Get Pets with Filter Param, Create Pet from Json File, Update Pet Param and Delete Created Pet
   Given User Select environment "<env>"
   And User Get pets with Filter "<key>" equal to "<value>" for "<getpath>"
   And User receive status code <codeget> for API get Pet
   When User Post Pet with "<filename>" 
   And User receive status code <codepost> for API post Pet
   And  Create Pet response should be valid
   And User Update Pet "<param>" with "<newvalue>" 
   And User receive status code <codeput> for API put Pet
   And  Update Pet response should be valid
   Then User Delete Pet with "<path_param>" 
   And User receive status code <codedelete> for API delete Pet
   And Delete Pet response should be valid
   
    Examples: 
    | env  |key    | value     |getpath        | codeget |filename            |codepost |codeput  |param  |newvalue  |path_param   |codedelete|
    | env1 |status | available |/findByStatus  | 200     |validPetData.json   |200      |200      |status |sold      |id=          |200       |
    | env1 |status | Sold      |/findByStatus  | 200     |validPetData.json   |200      |200      |status |Available |id=          |200       |
    | env1 |status | unkonw    |/findByStatus  | 200     |InvalidPetData.json |405      |405      |status |Available |id=          |200       |
 