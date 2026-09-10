data Cake = Cake 
    { 
        size :: String 
        , price :: Double  
    } 

pricyCake:: String -> Double -> Cake 
mkPricyCake pricyCakeSize pricyCakePrice = Cake 
	{ 
		size = pricyCakeSize 
		, price = pricyCakePrice 
	} 

-- The new freeCake type can be defined with its own default, but still be bundled as Cake 
freeCake:: String -> Cake 
mkFreeCake freeCakeSize = Cake 
	{ 
		size = freeCakeSize 
		, price = 0.0 
	} 

main :: IO () 
main = do 
    let pricyCake = mkPriceCake “Small” 2.50 
    let freeCake = mkFreeCake “Large”  