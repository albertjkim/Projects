
# MATH-UA 250 Mathematics of Finance
# Portfolio Management

library(lattice)
library(ggplot2)
library(fPortfolio)
install.packages("fPortfolio")
install.packages("matrixStats")

# file paths
inpath = "C:\\users\\Albert Kim\\Downloads\\"
outpath = "C:\\users\\Albert Kim\\Documents\\RStudioDocs\\MOF"

#
# read in the data
infile          = "ReturnsPortfolios.csv"
indata          = read.csv(file = paste0(inpath, infile), stringsAsFactors = FALSE)

head(indata)

#
# convert the date column to timeDate object
indata[,"Date"] = as.Date(indata[,"Date"],"%m/%d/%Y")


#
# print out the column names
print(colnames(indata))

# The file 
#  The asset classes in the file are
# "MSCI.WORLD"
# "MSCI.AC.WORLD"    
# "MSCI.EUROPE"
# "MSCI.EM"
# "MSCI.EAFE"        
# "MSCI.PACIFIC"
# "MSCI.USA"
# "Treasury.Bond.10Y"
# "Treasury.Bill.90D"


RiskyAsset     = c("MSCI.EUROPE","MSCI.USA","MSCI.PACIFIC","Treasury.Bond.10Y")
RiskFreeAsset  = "Treasury.Bill.90D"


# Part 1: Riskyt Assets Analysis
# 1. mean
# 2. median
# 3. standard deviations?
# 4. skew 
# 5. kurtosis
# 6. Return - risk ratio 
# 7. Plot the assets classes on a return - risk graph   
# 8. Write up a paragraph comparing the statistics of the risky assets
library(matrixStats)
mean = colMeans(indata[,RiskyAsset])
# 2.
median(indata$MSCI.EUROPE)
median(indata$MSCI.USA)
median(indata$MSCI.PACIFIC)
median(indata$Treasury.Bond.10Y)
colmedian <- c("Europe" = median(indata$MSCI.EUROPE), "USA" = median(indata$MSCI.USA), "Pacific" = median(indata$MSCI.PACIFIC),"TBond" =median(indata$Treasury.Bond.10Y)
)
colmedian
# 3.
  colsd <- c("Europe" = sd(indata$MSCI.EUROPE), "USA" = sd(indata$MSCI.USA), "Pacific" = sd(indata$MSCI.PACIFIC),"TBond" =sd(indata$Treasury.Bond.10Y))
# 4.
library(moments)
install.packages("moments")  
  skew = c(skewness(indata$MSCI.EUROPE), skewness(indata$MSCI.USA), skewness(indata$MSCI.PACIFIC), skewness(indata$Treasury.Bond.10Y))
  #5.
  kurt = c(kurtosis(indata$MSCI.EUROPE), kurtosis(indata$MSCI.USA), kurtosis(indata$MSCI.PACIFIC), kurtosis(indata$Treasury.Bond.10Y))
  #6 return / risk
  mean/colsd
  #7
  plot(mean,colsd)
  #8
  # the return for MSCI.USA is incredibly high and has risk lower than that of Europe, and Pacific. While Tbond is very safe it has 
  # low relative return. However, the pacific has very high risk for having returns less than the treasury bond. It is a dominated
  # option.
  
  # Part 2: Plot the distributions for each asset class
  # The PNG functions outputs the graph   
   #
   # example to output a histogream to the screen
   hist(indata[, "MSCI.EM"],  seq(-0.20,0.20,0.025), main = "MSCI EM", xlab = "Monthly Returns", col = "darkblue")
   grid()
   
   #
   # example to output to a png file
   png(file = paste0(outpath,"hist_test.png"))
    
     par( mfrow = c(1,1))
     hist(indata[, "MSCI.USA"],  seq(-0.20,0.20,0.025), main = "MSCI USA", xlab = "Monthly Returns", col = "darkblue")
     grid()
     
   dev.off()
   
   


# 
# Part 3: Covariance and Correlationa 
# 1.	Calculate the covariance matrix 
# 2.  Calculate the correlation matrix
# 3.	Verify the covariance matrix is non-singular
# 4.	Verify the covariance matrix is symmetric and positive definite

```{Python}
CovMatrix = indata.cov()
CorrMatrix = indata.corr()
non_singular = np.linalg.matrix_rank(CovMatrix) == CovMatrix.shape[0]
Symmetric = ((CovMatrix.transpose() == CovMatrix).all()).all()
PosDef = (np.linalg.eigvals(CovMatrix)>0).all()

```
   
   
# 
# Part 4: Calculate the weights for the following portfolios (no short sales)
# 1.	Minimum variance portfolio
# 2. Portfolio with expected return of 9% and minimum variance
# 3. Efficicent frontier (calculate the weights, returns and risk of at least 7 portfolios)
# 4. Plot the portfolios and asset classes on a graph
# 5. Plot an equal weighted portolio on the graph
# 6. What is the expected return and risk of the minimum variance portfolio
  
# 1.
  minvar <- function (indata){
    Rt <- indata[,RiskyAsset]
    cov.Rt <- cov(Rt)
    one.vec <- rep(1, nrow(cov.Rt))
    print(one.vec)
    num <- solve(cov.Rt) %*% one.vec
    print(num)
    den <- as.numeric(t(one.vec) %*% num)
    print(den)
    return(num/den)
  }
  mvp <- minvar(indata)
  print(minvar(indata))
# 2.
  expectedReturn <- function (indata) {
    temp <- indata[,RiskyAsset]
    sum.temp <- c(sum(temp[1]), sum(temp[2]), sum(temp[3]), sum(temp[4]))
    exp <- sum.temp/250
    return(exp)
  }
  ret <- expectedReturn(indata)
  #return on minimum variance portfolio is
  print(sum(mvp * ret))
  #standard deviation of the risky assets are
  RA <- indata[,RiskyAsset]
  var1 <- var(RA[1])
  var2 <- var(RA[2])
  var3 <- var(RA[3])
  var4 <- var(RA[4])
  vars <- c(var1, var2, var3, var4)
  sd <- sqrt(vars)
  print(sd)
  one.vec <- rep(1, ncol(cov))
  covInv <- solve(cov)
  retT <- t(ret)
  ret1 <- t(retT)
  dim(retT)
  dim(ret1)
  dim(covInv)
  dim(matrix(one.vec))
  one.vec <- matrix(one.vec)
  dim(one.vec)
  M <- matrix(c(retT %*% covInv %*% ret1,  retT%*%covInv%*%one.vec, t(one.vec) %*% covInv %*% ret1, t(one.vec)%*%covInv%*%one.vec), nrow = 2, ncol =2)
  lambda = 2 * solve(M) %*% matrix(c(0.0075,1)) #since it is monthly return not annual
  lambda1 <- lambda[1,]
  lambda2 <- lambda[2,]
  temp = lambda1 * retT %*% covInv + lambda2 * t(one.vec) %*% covInv
  weights = temp/2
  #weights for the portfolio
  weights 
    # 3.
  coordinate = matrix(nrow = 7, ncol = 2)
  
  coord1 = 2 * solve(M) %*% matrix(c(0.009,1))
  temp = coord1[1,] * retT %*% covInv + coord1[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = cbind(weights)
  coord2 = 2 * solve(M) %*% matrix(c(0.008,1))
  temp = coord2[1,] * retT %*% covInv + coord2[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  
  coord3 = 2 * solve(M) %*% matrix(c(0.007,1))
  temp = coord3[1,] * retT %*% covInv + coord3[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  
  coord4 = 2 * solve(M) %*% matrix(c(0.006,1))
  temp = coord4[1,] * retT %*% covInv + coord4[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  
  coord5 = 2 * solve(M) %*% matrix(c(0.005,1))
  temp = coord5[1,] * retT %*% covInv + coord5[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  
  coord6 = 2 * solve(M) %*% matrix(c(0.004,1))
  temp = coord6[1,] * retT %*% covInv + coord6[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  
  coord7 = 2 * solve(M) %*% matrix(c(0.003,1))
  temp = coord7[1,] * retT %*% covInv + coord7[2,] * t(one.vec) %*% covInv
  weights = temp/2
  coordinate = rbind(coordinate,weights)
  miu = coordinate[1,] %*% ret
  miu = rbind(miu, coordinate[2,] %*% ret)
  miu = rbind(miu, coordinate[3,] %*% ret)
  miu = rbind(miu, coordinate[4,] %*% ret)
  miu = rbind(miu, coordinate[5,] %*% ret)
  miu = rbind(miu, coordinate[6,] %*% ret)
  miu = rbind(miu, coordinate[7,] %*% ret)
  
  sd = t(matrix(coordinate[1,])) %*% cov %*% matrix(coordinate[1,])
  sd = rbind(sd, t(matrix(coordinate[2,])) %*% cov %*% matrix(coordinate[2,]))
  sd = rbind(sd, t(matrix(coordinate[3,])) %*% cov %*% matrix(coordinate[3,]))
  sd = rbind(sd, t(matrix(coordinate[4,])) %*% cov %*% matrix(coordinate[4,]))
  sd = rbind(sd, t(matrix(coordinate[5,])) %*% cov %*% matrix(coordinate[5,]))
  sd = rbind(sd, t(matrix(coordinate[6,])) %*% cov %*% matrix(coordinate[6,]))
  sd = rbind(sd, t(matrix(coordinate[7,])) %*% cov %*% matrix(coordinate[7,]))
  coordinate = cbind(miu, sd)
  #efficient frontier
  coordinate

  # 4.
  plot(coordinate)
  
  # 5.
  equalweight=t(matrix(c(.25,.25,.25,.25)))
  miuEQ = equalweight %*% ret
  sdEQ = equalweight %*% cov %*% t(equalweight)
  EQ = t(matrix(c(miuEQ, sdEQ)))
  coordinate = rbind(coordinate, EQ)
  plot(coordinate)
  # 6.
  mvpReturn <- mvp * ret
  mvpReturn
  mvpReturn <- sum(c(mvpReturn))
  #return
  mvpReturn
  mvpMatrix <- mvp[,1]
  mvpMatrix
  mvpMatrixT <- t(mvpMatrix)
  dim(mvp)
  mvpRisk <- sqrt(mvpMatrixT %*% cov %*% mvpMatrix)
  #risk
  mvpRisk
# 
# Part 5: Calculate the beta (t-stat, R-square) of each asset class to the following 'market' portfolios
   # "MSCI.AC.WORLD"    
   # "MSCI.USA"
   # Compare the betas for each asset class
   
#MSCI.WORLD VS ASSET CLASSES
   BetaEurope = lm(MSCI.WORLD ~ MSCI.EUROPE, data= indata)
   summary(BetaEurope)
   BetaPACIFIC = lm(MSCI.WORLD ~ MSCI.PACIFIC, data= indata)
   summary(BetaEurope)
   BetaUSA = lm(MSCI.WORLD ~ MSCI.USA, data= indata)
   summary(BetaUSA)
   BetaTBOND = lm(MSCI.WORLD ~ Treasury.Bond.10Y, data = indata)
    summany(BetaTBOND)
   table = c( Beta.world =coef(BetaPACIFIC), coef(BetaUSA), coef(BetaEurope), coef(BetaTBOND))
   print(table)
   
#MSCI.USA VS ASSET CLASSES
   
   BetaEurope2 = lm(MSCI.USA ~ MSCI.EUROPE, data = indata)
   BetaPACIFIC2 = lm(MSCI.USA ~ MSCI.PACIFIC, data=indata)
   BetaTBOND2 = lm(MSCI.USA ~ Treasury.Bond.10Y, data = indata)
   BetaUSA2 = lm(MSCI.USA ~ MSCI.USA, data = indata)
   summary(BetaEurope2)
   summary(BetaPACIFIC2)
   summary(BetaTBOND2)
   summary(BetaUSA2)
   table2 <- c(BetaEurope2$coefficients,BetaPACIFIC2$coefficients,BetaTBOND2$coefficients,BetaUSA2$coefficients)
   print(table2)
   cov2 <- cov(indata$MSCI.AC.WORLD,indata$MSCI.WORLD)
   var2 <- var(indata$MSCI.AC.WORLD)
   beta2 <- cov2/var2
   print(beta2)


