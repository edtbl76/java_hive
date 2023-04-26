# Best Practices for Using Secure Random Number
- Never accept defaults. 
    - always specify the PRNG AND the PROVIDER
    
    
    EX:
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

## SHA1PRNG vs. NativePRNG
- PRNG = Pseudo Random Number Generator

SHA1PRNG
- seeds from /dev/urandom
- up to 17 x faster than Native PRNG
- BUT seeding optinos are fixed

NativePRNG
- flexible
- blocks if entropy isn't great enough because it reads from /dev/random.

### List of PRNGS for SUN

SHA1PRNG
- initial seeding is done via a combination of system attributes and the java.security entropy 
gathering device

NativePRNG 
- nextBytes() uses /dev/urandom
- generateSeed() uses /dev/random

NativePRNGBlocking
- nextBytes() AND generateSeed() use /dev/random
- Java8+

NativePRNGNonBlocking
- nextBytes() AND generateSeed() use /dev/urandom
- Java8+
    
    
    