import unittest
from selenium import webdriver
import time



class TestStringMethods(unittest.TestCase):

      
 
    def test_1_wrong_credentials(self):
        
        driver.get(ip)
     
        driver.execute_script("document.getElementsByTagName('input')[0].value='Λάμπρος'")
        
        driver.execute_script("document.getElementsByTagName('input')[1].value='άσε με να μπω'")
        
        driver.execute_script("document.getElementsByClassName('btn btn-primary')[0].click()")
        time.sleep(3)
        
        self.assertGreater(
                driver.execute_script(
                "return document.getElementsByClassName('alert alert-danger col-xs-offset-1 col-xs-10').\
                length;"),0)
       
       

    def test_2_right_credentials(self):

        driver.get(ip)
         
        driver.execute_script("document.getElementsByTagName('input')[0].value='admin'")
        
        driver.execute_script("document.getElementsByTagName('input')[1].value='pass'")
        
        driver.execute_script("document.getElementsByClassName('btn btn-primary')[0].click()")
        time.sleep(5)
        
        self.assertTrue(
                driver.execute_script(
                "return document.getElementById('dataTable')!=null;"))
        
        driver.close()



if __name__ == '__main__':
    #print('Enter server\'s ip:')
    #ip = input()
    #print('Server\'s ip is :{} running tests on it'.format(x)) 
    ip='http://localhost:8080/HMS/login'
    driver =webdriver.Firefox(executable_path='./geckodriver');
   
      
    unittest.main()