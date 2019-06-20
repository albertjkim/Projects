
# coding: utf-8

# Question 1: Monte Carol Simulation 

# In[15]:


from time import time
from math import exp, sqrt, log
from random import gauss, seed
from IPython  import get_ipython
get_ipython().magic('matplotlib inline')

import numpy as np
import matplotlib.pyplot as plt


# In[72]:


S0 = 100
T = 1.0
r = 0.05
sigma = 0.20
N = 50
dt = T/N
I = 5000


# In[73]:


seed(2000)
S=[]
for i in range(I):
    path = []
    for t in range(N+1):
        if t == 0:
            path.append(S0)
            St = 100
        else:
            wt = gauss(0.0,1.0)
            St = St * exp((r - 0.5*sigma**2) * dt + sigma * sqrt(dt) * wt)
            path.append(St)
    S.append(path)


# In[91]:


plt.plot(S[1])
plt.plot(S[2])
plt.plot(S[3])
plt.plot(S[4])
plt.plot(S[5])
plt.plot(S[6])
for i in range(4):
    plt.plot(S[i+7])
plt.grid(True)
plt.xlabel('time step')
plt.ylabel('idenx label')


# Question 2: Pricing options
# 
# a) Call with Strike 105, Maturity T = 1

# In[92]:


seed(2000)
SA=[]
for i in range(I):
    pathA = []
    for t in range(N+1):
        if t == 0:
            pathA.append(S0)
            St = 100
        else:
            wt = gauss(0.0,1.0)
            St = St * exp((r - 0.5*sigma**2) * dt + sigma * sqrt(dt) * wt)
            #
            # add code for St 
            # St = ...
            pathA.append(St)
    SA.append(pathA)
sum = 0
for i in range(len(SA)):
    if SA[i][50] >= 105:
        sum += SA[i][50] - 105
print(sum/len(SA))


# The price of this call option is $8.64

# b) Put with Strike = 105, Maturity T = 1

# In[93]:


seed(2000)
SA=[]
for i in range(I):
    pathA = []
    for t in range(N+1):
        if t == 0:
            pathA.append(S0)
            St = 100
        else:
            wt = gauss(0.0,1.0)
            St = St * exp((r - 0.5*sigma**2) * dt + sigma * sqrt(dt) * wt)
            #
            # add code for St 
            # St = ...
            pathA.append(St)
    SA.append(pathA)
sum = 0
for i in range(len(SA)):
    if SA[i][50] <= 105:
        sum +=105 -  SA[i][50]
print(sum/len(SA))


# The price of this put option is $8.20

# c) Call with Strike (S-K)^2, Maturity T = 1

# In[94]:


seed(2000)
SA=[]
for i in range(I):
    pathA = []
    for t in range(N+1):
        if t == 0:
            pathA.append(S0)
            St = 100
        else:
            wt = gauss(0.0,1.0)
            St = St * exp((r - 0.5*sigma**2) * dt + sigma * sqrt(dt) * wt)
            #
            # add code for St 
            # St = ...
            pathA.append(St)
    SA.append(pathA)
sum = 0
for i in range(len(SA)):
    if SA[i][50] <= 105:
        sum +=(SA[i][50] - 105)**2
print(sum/len(SA))


# The price of this call option is $185.52
